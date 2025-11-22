package com.sarim.convention

import com.sarim.convention.utils.AndroidExtraModulesConventionExtension
import com.sarim.convention.utils.ModuleName
import com.sarim.convention.utils.ModuleType
import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidPresentationLibraryConventionPlugin : Plugin<Project> {
    @Suppress("LongMethod")
    override fun apply(target: Project) =
        with(target) {
            pluginManager.apply(
                libs.plugins.androidLibraryPlugin
                    .get()
                    .pluginId,
            )
            pluginManager.apply(
                libs.plugins.kotlinAndroidPlugin
                    .get()
                    .pluginId,
            )
            pluginManager.apply(
                libs.plugins.kotlinComposePlugin
                    .get()
                    .pluginId,
            )
            pluginManager.apply(
                libs.plugins.kotlinSerializationPlugin
                    .get()
                    .pluginId,
            )
            pluginManager.apply(
                libs.plugins.conventionPluginJacocoId
                    .get()
                    .pluginId,
            )
            pluginManager.apply("kotlin-parcelize")

            val inferredNamespace = project.name.replace("-", "_")
            configureAndroidLibrary(
                namespace = inferredNamespace,
                useCompose = true,
            )

            val extension =
                extensions.create(
                    "extraModules",
                    AndroidExtraModulesConventionExtension::class.java,
                )

            afterEvaluate {
                configureModuleDependencies(
                    modules =
                        (
                            listOf(
                                ":utils",
                                ":ui",
                            ) + extension.modules
                        ).map {
                            ModuleName(
                                moduleType = ModuleType.IMPLEMENTATION,
                                name = it,
                            )
                        },
                )
            }

            dependencies {
                "implementation"(libs.androidxCoreKtxLibrary)
                "implementation"(libs.androidxLifecycleRuntimeKtxLibrary)
                "implementation"(libs.androidxActivityComposeLibrary)
                "implementation"(libs.bundles.dataStorageBundle)
                "implementation"(platform(libs.androidxComposeBomLibrary))
                "implementation"(libs.bundles.composeImplementationBundle)
                "implementation"(platform(libs.koinBomLibrary))
                "implementation"(libs.bundles.koinBundle)
                "debugImplementation"(platform(libs.androidxComposeBomLibrary))
                "debugImplementation"(libs.bundles.composeDebugImplementationBundle)
                "testImplementation"(platform(libs.androidxComposeBomLibrary))
                "testImplementation"(platform(libs.junitBomLibrary))
                "testImplementation"(libs.bundles.testBundle)
                "testRuntimeOnly"(libs.junitPlatformibrary)
                add("testImplementation", testFixtures(project(":utils")))
            }
        }
}
