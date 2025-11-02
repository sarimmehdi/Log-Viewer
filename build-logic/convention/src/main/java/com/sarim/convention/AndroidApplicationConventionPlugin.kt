package com.sarim.convention

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.androidApplicationPlugin.get().pluginId)
        pluginManager.apply(libs.plugins.kotlinAndroidPlugin.get().pluginId)
        pluginManager.apply(libs.plugins.kotlinComposePlugin.get().pluginId)

        extensions.configure<BaseAppModuleExtension> {
            namespace = "com.sarim.logviewer"
            compileSdk = 36

            defaultConfig {
                applicationId = "com.sarim.logviewer"
                minSdk = 26
                targetSdk = 36
                versionCode = 1
                versionName = "1.0"
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            buildFeatures {
                compose = true
            }
        }
        tasks.withType(KotlinCompile::class.java).configureEach {
            compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
        }

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
            "debugImplementation"(libs.bundles.composeImplementationBundle)
        }
        afterEvaluate {
            configureModuleDependencies(
                modules = listOf(
                    ":utils",
                    ":nav",
                    ":sidebar:sidebar-di",
                    ":sidebar:sidebar-data",
                    ":sidebar:sidebar-domain",
                    ":sidebar:sidebar-presentation",
                )
            )
        }
    }
}