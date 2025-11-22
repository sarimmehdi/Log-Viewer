package com.sarim.convention

import com.android.build.api.dsl.LibraryExtension
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

class JacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val jacocoToolVersion = libs.versions.jacocoVersion.get()
            if (target == target.rootProject) {
                configureAggregatedReport(jacocoToolVersion)
            } else {
                configureJacocoForSubproject(jacocoToolVersion)
            }
        }
    }

    private fun Project.configureJacocoForSubproject(toolVersion: String) {
        pluginManager.apply("jacoco")
        extensions.configure<JacocoPluginExtension> {
            this.toolVersion = toolVersion
        }
        extensions.configure<LibraryExtension> {
            buildTypes {
                debug {
                    enableUnitTestCoverage = true
                }
            }
        }
        logger.lifecycle("JaCoCo configured for module: ${project.path}")
    }

    @Suppress("LongMethod")
    private fun Project.configureAggregatedReport(toolVersion: String) {
        pluginManager.apply("jacoco")
        extensions.configure<JacocoPluginExtension> {
            this.toolVersion = toolVersion
        }

        tasks.register<JacocoReport>("jacocoAggregatedReport") {
            group = "verification"
            description = "Generates JaCoCo code coverage reports for the debug build."

            val projectsToInclude =
                subprojects.filter { subproject ->
                    subproject.plugins.hasPlugin("jacoco")
                }

            if (projectsToInclude.isNotEmpty()) {
                logger.lifecycle(
                    "JacocoAggregatedReport: Will aggregate coverage for projects: " +
                        "${projectsToInclude.map { it.path }}",
                )
                reports {
                    html.required.set(true)
                    xml.required.set(true)
                    csv.required.set(false)
                }

                val excludePatterns =
                    listOf(
                        "**/Preview**",
                        "**/*Preview*",
                        "**/*Preview",
                        "**/*PreviewKt*",
                        "**/*PreviewData*",
                        "**/*ParameterProvider*",
                        "**/ComposableSingletons*",
                        "**/*SnackbarController*",
                        "**/*SnackbarEvent*",
                        "**/*SnackbarAction*",
                        "**/*DtoSerializer*",
                        "**/*RepositoryImpl*",
                        "**/*Dao*",
                        "**/*DtoFts",
                        "**/*Database",
                        "**/*Database*",
                        "**/*_di/**",
                        "**/ui/theme/**",
                        "**/utils/**",
                    )

                sourceDirectories.setFrom(
                    projectsToInclude.flatMap { module ->
                        listOf(
                            module.fileTree("${module.projectDir}/src/main/java"),
                            module.fileTree("${module.projectDir}/src/main/kotlin"),
                        )
                    },
                )

                // Use files() with filter instead of fileTree exclude
                classDirectories.setFrom(
                    files(
                        projectsToInclude.map { module ->
                            module
                                .fileTree(
                                    module.layout.buildDirectory.dir("tmp/kotlin-classes/debug"),
                                ).matching {
                                    include("**/*.class")
                                    exclude(excludePatterns)
                                }
                        },
                    ),
                )

                doFirst {
                    logger.lifecycle("=== Class Directories Debug ===")
                    val previewFiles =
                        classDirectories.asFileTree.files.filter {
                            it.name.contains("Preview", ignoreCase = true)
                        }
                    logger.lifecycle("Preview files count: ${previewFiles.size}")
                    previewFiles.forEach {
                        logger.lifecycle("STILL INCLUDED: ${it.name}")
                    }
                    logger.lifecycle("=== End Debug ===")
                }

                executionData.setFrom(
                    projectsToInclude
                        .flatMap { module ->
                            val unitTestExecFile =
                                module.layout.buildDirectory
                                    .file(
                                        "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec",
                                    ).orNull
                                    ?.asFile
                            val roboElectricTestExecFile =
                                module.layout.buildDirectory
                                    .file(
                                        "jacoco/robolectricTest.exec",
                                    ).orNull
                                    ?.asFile

                            val filesToInclude = mutableListOf<Any>()
                            if (unitTestExecFile?.exists() == true) {
                                logger.lifecycle("JacocoAggregatedReport: Found .exec file $unitTestExecFile")
                                filesToInclude.add(unitTestExecFile)
                            }
                            if (roboElectricTestExecFile?.exists() == true) {
                                logger.lifecycle("JacocoAggregatedReport: Found .exec file $roboElectricTestExecFile")
                                filesToInclude.add(roboElectricTestExecFile)
                            }
                            filesToInclude
                        }.filter {
                            when (it) {
                                is java.io.File -> it.exists()
                                is org.gradle.api.file.FileTree -> !it.isEmpty
                                else -> false
                            }
                        },
                )
            } else {
                enabled = false
                project.logger.error("JaCoCo: No specified modules found for coverage report.")
            }
        }
    }
}
