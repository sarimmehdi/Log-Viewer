package com.sarim.convention

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.sarim.convention.utils.Config
import com.sarim.convention.utils.DependencyType
import com.sarim.convention.utils.configureModuleDependencies
import com.sarim.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) =
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

            val config = Config()
            extensions.configure<BaseAppModuleExtension> {
                namespace = config.baseNamespace + ".logviewer"
                compileSdk = config.compileSdk

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
                        proguardFiles(
                            getDefaultProguardFile(config.defaultProguardFileName),
                            config.proGuardFileName,
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
                "debugImplementation"(platform(libs.androidxComposeBomLibrary))
                "debugImplementation"(libs.bundles.composeImplementationBundle)
                "testImplementation"(libs.bundles.testBundle)
            }
            afterEvaluate {
                configureModuleDependencies(
                    modules =
                        listOf(
                            Pair(":utils", DependencyType.IMPLEMENTATION),
                            Pair(":nav", DependencyType.IMPLEMENTATION),
                            Pair(
                                ":sidebar:sidebar-dates:sidebar-dates-di",
                                DependencyType.IMPLEMENTATION,
                            ),
                            Pair(
                                ":sidebar:sidebar-dates:sidebar-dates-data",
                                DependencyType.IMPLEMENTATION,
                            ),
                            Pair(
                                ":sidebar:sidebar-dates:sidebar-dates-domain",
                                DependencyType.IMPLEMENTATION,
                            ),
                            Pair(
                                ":sidebar:sidebar-dates:sidebar-dates-presentation",
                                DependencyType.IMPLEMENTATION,
                            ),
                            Pair(
                                ":sidebar:sidebar-sessions:sidebar-sessions-di",
                                DependencyType.IMPLEMENTATION,
                            ),
                            Pair(
                                ":sidebar:sidebar-sessions:sidebar-sessions-data",
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
}
