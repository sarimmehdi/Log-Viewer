package com.sarim.convention.utils

import com.android.build.api.dsl.LibraryExtension
import com.sarim.convention.task.CreateRoboelectricTestTask
import com.sarim.convention.task.CreateRoboelectricTestTask.create as createRoboelectricTestTask
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureAndroidLibrary(
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
        }

        buildTypes {
            getByName(config.releaseBuildTypeName) {
                isMinifyEnabled = false
            }
        }

        compileOptions {
            sourceCompatibility = config.sourceCompatibility
            targetCompatibility = config.targetCompatibility
        }

        buildFeatures {
            compose = useCompose
        }
        buildFeatures {
            buildConfig = true
        }
        testFixtures {
            enable = true
        }
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }

    createRoboelectricTestTask()

    tasks.withType(Test::class.java).configureEach {
        if (name != CreateRoboelectricTestTask.TASK_NAME) {
            useJUnitPlatform()
        }
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    tasks.withType(KotlinCompile::class.java).configureEach {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
    }
}
