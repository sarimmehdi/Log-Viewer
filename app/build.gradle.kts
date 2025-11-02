import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplicationPlugin)
    alias(libs.plugins.kotlinAndroidPlugin)
    alias(libs.plugins.kotlinComposePlugin)
    alias(libs.plugins.ktlintPlugin)
}

android {
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
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidxCoreKtxLibrary)
    implementation(libs.androidxLifecycleRuntimeKtxLibrary)
    implementation(libs.androidxActivityComposeLibrary)
    implementation(platform(libs.androidxComposeBomLibrary))
    implementation(libs.bundles.composeImplementationBundle)
    implementation(platform(libs.koinBomLibrary))
    implementation(libs.bundles.koinBundle)
    implementation(libs.bundles.dataStorageBundle)
    implementation(project(":utils"))
    implementation(project(":nav"))
    implementation(project(":sidebar:sidebar-di"))
    implementation(project(":sidebar:sidebar-data"))
    implementation(project(":sidebar:sidebar-domain"))
    implementation(project(":sidebar:sidebar-presentation"))
    debugImplementation(platform(libs.androidxComposeBomLibrary))
    debugImplementation(libs.bundles.composeImplementationBundle)
}
