import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidLibraryPlugin)
    alias(libs.plugins.kotlinAndroidPlugin)
    alias(libs.plugins.ktlintPlugin)
}

android {
    namespace = "com.sarim.sidebar_di"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

dependencies {
    implementation(platform(libs.koinBomLibrary))
    implementation(libs.bundles.koinBundle)
    implementation(project(":utils"))
    implementation(project(":sidebar:sidebar-di"))
    implementation(project(":sidebar:sidebar-data"))
    implementation(project(":sidebar:sidebar-domain"))
    implementation(project(":sidebar:sidebar-presentation"))
}
