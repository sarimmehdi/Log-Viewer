package com.sarim.convention.utils

open class AndroidDataStorageConventionExtension {
    var dataStorageName: String = ""
    var dataStorageMode: StorageMode = StorageMode.REAL
    var databaseName: String = ""
    var databaseStorageMode: StorageMode = StorageMode.REAL
}

enum class StorageMode(
    val storageMode: String,
) {
    REAL("real"),
    IN_MEMORY("in_memory"),
    ERROR("error"),
}
