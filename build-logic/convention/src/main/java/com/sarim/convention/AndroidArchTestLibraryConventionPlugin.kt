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
                        ":sidebar:sidebar-dates:sidebar-dates-di",
                        ":sidebar:sidebar-dates:sidebar-dates-data",
                        ":sidebar:sidebar-dates:sidebar-dates-domain",
                        ":sidebar:sidebar-dates:sidebar-dates-presentation",
                        ":sidebar:sidebar-sessions:sidebar-sessions-di",
                        ":sidebar:sidebar-sessions:sidebar-sessions-data",
                        ":sidebar:sidebar-sessions:sidebar-sessions-domain",
                        ":sidebar:sidebar-sessions:sidebar-sessions-presentation",
                        ":maincontent:maincontent-di",
                        ":maincontent:maincontent-data",
                        ":maincontent:maincontent-domain",
                        ":maincontent:maincontent-presentation",
                    ),
            )
        }
}
