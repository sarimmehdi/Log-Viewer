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

    implementation(libs.kotlinXImmutableCollectionsLibrary)
    implementation(libs.androidxCoreKtxLibrary)
    implementation(libs.androidxLifecycleRuntimeKtxLibrary)
    implementation(libs.androidxActivityComposeLibrary)
    implementation(platform(libs.androidxComposeBomLibrary))
    implementation(libs.androidxComposeUiLibrary)
    implementation(libs.androidxComposeUiGraphicsLibrary)
    implementation(libs.androidxComposeUiToolingPreviewLibrary)
    implementation(libs.androidxComposeMaterial3Library)
    implementation(platform(libs.koinBomLibrary))
    implementation(libs.bundles.koinBundle)
    implementation(project(":utils"))
    implementation(project(":sidebar:sidebar-domain"))
    implementation(project(":sidebar:sidebar-presentation"))
    testImplementation(libs.junitLibrary)
    androidTestImplementation(libs.androidxJunitLibrary)
    androidTestImplementation(libs.androidxEspressoCoreLibrary)
    androidTestImplementation(platform(libs.androidxComposeBomLibrary))
    androidTestImplementation(libs.androidxComposeUiTestJunit4Library)
    debugImplementation(libs.androidxComposeUiToolingLibrary)
    debugImplementation(libs.androidxComposeUiToolingPreviewLibrary)
    debugImplementation(libs.androidxComposeUiTestManifestLibrary)
}
