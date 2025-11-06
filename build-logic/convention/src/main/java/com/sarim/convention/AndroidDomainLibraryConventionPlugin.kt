package com.sarim.convention

import com.sarim.convention.utils.DependencyType
import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDomainLibraryConventionPlugin : Plugin<Project> {
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
            pluginManager.apply("kotlin-parcelize")

            val inferredNamespace = project.name.replace("-", "_")
            configureAndroidLibrary(
                namespace = inferredNamespace,
                useCompose = false,
            )

            afterEvaluate {
                configureModuleDependencies(
                    modules =
                        listOf(
                            Pair(
                                ":utils",
                                DependencyType.IMPLEMENTATION,
                            ),
                        ),
                )
            }

            dependencies {
                "implementation"(libs.androidxCoreKtxLibrary)
                "implementation"(platform(libs.koinBomLibrary))
                "implementation"(libs.bundles.koinBundle)
                "implementation"(libs.bundles.dataStorageBundle)
            }
        }
}
