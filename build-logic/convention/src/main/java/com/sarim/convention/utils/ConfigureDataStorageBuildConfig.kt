package com.sarim.convention.utils

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

internal fun Project.configureDataStorageBuildConfig(dataStorageExtension: AndroidDataStorageConventionExtension) {
    if (dataStorageExtension.dataStorageName.isNotEmpty()) {
        val libraryExtension = extensions.getByType(LibraryExtension::class.java)
        libraryExtension.defaultConfig.apply {
            val value = dataStorageExtension.dataStorageMode.storageMode
            buildConfigField("String", dataStorageExtension.dataStorageName, "\"$value\"")
        }
    }
    if (dataStorageExtension.databaseName.isNotEmpty()) {
        val libraryExtension = extensions.getByType(LibraryExtension::class.java)
        libraryExtension.defaultConfig.apply {
            val value = dataStorageExtension.dataStorageMode.storageMode
            buildConfigField("String", dataStorageExtension.databaseName, "\"$value\"")
        }
    }
}
