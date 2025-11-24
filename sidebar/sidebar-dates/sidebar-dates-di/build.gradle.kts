import com.sarim.convention.utils.StorageMode

plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":sidebar:sidebar-dates:sidebar-dates-data",
            ":sidebar:sidebar-dates:sidebar-dates-domain",
            ":sidebar:sidebar-dates:sidebar-dates-presentation",
        )
}

dataStorage {
    dataStorageName = "DATE_DTO_DATASTORE"
    dataStorageMode = StorageMode.REAL
    databaseName = "DATE_DTO_DATABASE"
    databaseStorageMode = StorageMode.REAL
}
