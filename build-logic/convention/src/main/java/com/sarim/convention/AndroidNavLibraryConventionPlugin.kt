package com.sarim.convention

import com.sarim.convention.utils.DependencyType
import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidNavLibraryConventionPlugin : Plugin<Project> {
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
            configureAndroidLibrary(
                namespace = "nav",
                useCompose = true,
            )

            dependencies {
                "implementation"(platform(libs.androidxComposeBomLibrary))
                "implementation"(libs.bundles.composeImplementationBundle)
                "implementation"(libs.bundles.dataStorageBundle)
                "implementation"(libs.androidxCoreKtxLibrary)
                "implementation"(libs.bundles.navBundle)
                "implementation"(libs.kotlinxSerializationCoreLibrary)
                "implementation"(platform(libs.koinBomLibrary))
                "implementation"(libs.bundles.koinBundle)
            }
            configureModuleDependencies(
                modules =
                    listOf(
                        Pair(":utils", DependencyType.IMPLEMENTATION),
                        Pair(
                            ":sidebar:sidebar-dates:sidebar-dates-domain",
                            DependencyType.IMPLEMENTATION,
                        ),
                        Pair(
                            ":sidebar:sidebar-dates:sidebar-dates-presentation",
                            DependencyType.IMPLEMENTATION,
                        ),
                        Pair(
                            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
                            DependencyType.IMPLEMENTATION,
                        ),
                        Pair(
                            ":sidebar:sidebar-sessions:sidebar-sessions-presentation",
                            DependencyType.IMPLEMENTATION,
                        ),
                    ),
            )
        }
}
