package com.sarim.convention

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.sarim.convention.utils.Config
import com.sarim.convention.utils.ModuleName
import com.sarim.convention.utils.ModuleType
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal class AndroidApplicationConventionPlugin : Plugin<Project> {
    @Suppress("LongMethod")
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                libs.plugins.androidApplicationPlugin
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
                libs.plugins.kotzillaPlugin
                    .get()
                    .pluginId,
            )

            val config = Config()
            extensions.configure<BaseAppModuleExtension> {
                namespace = config.baseNamespace + ".logviewer"
                compileSdk = config.compileSdk
                buildToolsVersion = config.buildToolsVersion

                defaultConfig {
                    applicationId = config.applicationId
                    minSdk = config.minSdk
                    targetSdk = config.targetSdk
                    versionCode = config.versionCode
                    versionName = config.versionName
                    testInstrumentationRunner = config.testInstrumentationRunner
                }
                buildTypes {
                    getByName(config.releaseBuildTypeName) {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile(config.defaultProguardFileName),
                        )
                    }
                }
                compileOptions {
                    sourceCompatibility = config.sourceCompatibility
                    targetCompatibility = config.targetCompatibility
                }
                buildFeatures {
                    compose = true
                    buildConfig = true
                }
            }
            tasks.withType(KotlinCompile::class.java).configureEach {
                compilerOptions.jvmTarget.set(config.jvmTarget)
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
                "implementation"(libs.kotzillaSdkComposeLibrary)
                "testImplementation"(platform(libs.junitBomLibrary))
                "testImplementation"(libs.bundles.testBundle)
                "testRuntimeOnly"(libs.junitPlatformibrary)
            }
            afterEvaluate {
                configureModuleDependencies(
                    modules =
                        listOf(
                            ":utils",
                            ":nav",
                            ":ui",
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
                            ":footer:footer-di",
                            ":footer:footer-data",
                            ":footer:footer-domain",
                            ":footer:footer-presentation",
                        ).map { ModuleName(moduleType = ModuleType.IMPLEMENTATION, name = it) },
                )
            }
        }
    }
}
