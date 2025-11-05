package com.sarim.convention.utils

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.JavaVersion

fun Project.configureAndroidLibrary(
    namespace: String,
    useCompose: Boolean,
) {
    val config = Config()
    extensions.configure<LibraryExtension> {
        this.namespace = config.baseNamespace + ".$namespace"
        compileSdk = config.compileSdk

        defaultConfig {
            minSdk = config.minSdk
            testInstrumentationRunner = config.testInstrumentationRunner
            consumerProguardFiles(config.consumerProGuardFileName)
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

        if (useCompose) {
            buildFeatures {
                compose = true
            }
        }
        buildFeatures {
            buildConfig = true
        }
    }

    tasks.withType(KotlinCompile::class.java).configureEach {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
    }
}
