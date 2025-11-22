package com.sarim.convention.utils

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal data class Config(
    val baseNamespace: String = "com.sarim",
    val applicationId: String = "com.sarim.logviewer",
    val compileSdk: Int = 36,
    val buildToolsVersion: String = "36.0.0",
    val minSdk: Int = 26,
    val targetSdk: Int = 36,
    val versionCode: Int = 1,
    val versionName: String = "1.0",
    val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner",
    val releaseBuildTypeName: String = "release",
    val defaultProguardFileName: String = "proguard-android-optimize.txt",
    val sourceCompatibility: JavaVersion = JavaVersion.VERSION_17,
    val targetCompatibility: JavaVersion = JavaVersion.VERSION_17,
    val jvmTarget: JvmTarget = JvmTarget.JVM_17,
)
