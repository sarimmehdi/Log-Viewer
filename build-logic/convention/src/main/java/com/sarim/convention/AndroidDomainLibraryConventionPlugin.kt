package com.sarim.convention

import com.sarim.convention.utils.ModuleName
import com.sarim.convention.utils.ModuleType
import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidDomainLibraryConventionPlugin : Plugin<Project> {
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
                useCompose = false,
            )

            afterEvaluate {
                configureModuleDependencies(
                    modules =
                        listOf(":utils").map {
                            ModuleName(
                                moduleType = ModuleType.IMPLEMENTATION,
                                name = it,
                            )
                        },
                )
            }

            dependencies {
                "implementation"(libs.androidxCoreKtxLibrary)
                "implementation"(platform(libs.koinBomLibrary))
                "implementation"(libs.bundles.koinBundle)
                "implementation"(libs.bundles.dataStorageBundle)
                "testImplementation"(platform(libs.junitBomLibrary))
                "testImplementation"(libs.bundles.testBundle)
                "testRuntimeOnly"(libs.junitPlatformibrary)
            }
        }
}
