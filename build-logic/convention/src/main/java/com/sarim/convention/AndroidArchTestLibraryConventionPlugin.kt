package com.sarim.convention

import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidArchTestLibraryConventionPlugin : Plugin<Project> {
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

            configureAndroidLibrary(
                namespace = "arch_test",
                useCompose = false,
            )

            dependencies {
                "testImplementation"(libs.bundles.testBundle)
            }
            configureModuleDependencies(
                modules =
                    listOf(
                        ":sidebar:sidebar-di",
                        ":sidebar:sidebar-data",
                        ":sidebar:sidebar-domain",
                        ":sidebar:sidebar-presentation",
                        ":maincontent:maincontent-di",
                        ":maincontent:maincontent-data",
                        ":maincontent:maincontent-domain",
                        ":maincontent:maincontent-presentation",
                    ),
            )
        }
}
