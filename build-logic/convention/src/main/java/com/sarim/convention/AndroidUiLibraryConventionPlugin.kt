package com.sarim.convention

import com.sarim.convention.utils.ModuleName
import com.sarim.convention.utils.ModuleType
import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidUiLibraryConventionPlugin : Plugin<Project> {
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
            configureAndroidLibrary(
                namespace = "ui",
                useCompose = true,
            )

            dependencies {
                "implementation"(platform(libs.androidxComposeBomLibrary))
                "implementation"(libs.bundles.composeImplementationBundle)
                "implementation"(libs.androidxCoreKtxLibrary)
                "debugImplementation"(platform(libs.androidxComposeBomLibrary))
                "debugImplementation"(libs.bundles.composeDebugImplementationBundle)
            }
            configureModuleDependencies(
                modules =
                    listOf(
                        ":utils",
                    ).map { ModuleName(moduleType = ModuleType.IMPLEMENTATION, name = it) },
            )
        }
}
