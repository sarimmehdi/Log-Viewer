package com.sarim.convention

import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidUtilsLibraryConventionPlugin : Plugin<Project> {
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
            pluginManager.apply("kotlin-parcelize")

            configureAndroidLibrary(
                namespace = "utils",
                useCompose = true,
            )

            dependencies {
                "implementation"(libs.androidxCoreKtxLibrary)
                "implementation"(libs.androidxLifecycleRuntimeKtxLibrary)
                "implementation"(libs.androidxActivityComposeLibrary)
                "implementation"(platform(libs.androidxComposeBomLibrary))
                "implementation"(libs.bundles.composeImplementationBundle)
                "implementation"(platform(libs.koinBomLibrary))
                "implementation"(libs.bundles.koinBundle)
                "implementation"(libs.bundles.dataStorageBundle)
                "debugImplementation"(platform(libs.androidxComposeBomLibrary))
                "debugImplementation"(libs.bundles.composeDebugImplementationBundle)
            }
        }
}
