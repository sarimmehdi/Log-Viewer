package com.sarim.convention

import com.sarim.convention.utils.AndroidExtraModulesConventionExtension
import com.sarim.convention.utils.configureAndroidLibrary
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDataLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.androidLibraryPlugin.get().pluginId)
        pluginManager.apply(libs.plugins.kotlinAndroidPlugin.get().pluginId)

        configureAndroidLibrary(
            namespace = "com.sarim.sidebar_data",
            useCompose = false,
        )

        dependencies {
            "implementation"(libs.androidxCoreKtxLibrary)
            "implementation"(platform(libs.koinBomLibrary))
            "implementation"(libs.bundles.koinBundle)
            "implementation"(project(":utils"))
            "implementation"(project(":sidebar:sidebar-domain"))
        }
        val extension = target.extensions.create(
            "extraModules",
            AndroidExtraModulesConventionExtension::class.java
        )
        afterEvaluate {
            configureModuleDependencies(
                modules = listOf(
                    ":utils",
                ) + extension.modules
            )
        }
    }
}