package com.sarim.convention

import com.sarim.convention.utils.AndroidExtraModulesConventionExtension
import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDiLibraryConventionPlugin : Plugin<Project> {
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

            val inferredNamespace = project.name.replace("-", "_")
            configureAndroidLibrary(
                namespace = inferredNamespace,
                useCompose = false,
            )

            val extension =
                extensions.create(
                    "extraModules",
                    AndroidExtraModulesConventionExtension::class.java,
                )

            afterEvaluate {
                configureModuleDependencies(
                    modules = listOf(":utils") + extension.modules,
                )
            }

            dependencies {
                "implementation"(platform(libs.koinBomLibrary))
                "implementation"(libs.bundles.koinBundle)
            }
        }
}
