import com.sarim.convention.utils.StorageMode

plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":sidebar:sidebar-dates:sidebar-dates-data",
            ":sidebar:sidebar-sessions:sidebar-sessions-data",
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
            ":sidebar:sidebar-sessions:sidebar-sessions-presentation",
        )
}

dataStorage {
    dataStorageName = "SESSION_DTO_DATASTORE"
    dataStorageMode = StorageMode.REAL
    databaseName = "SESSION_DTO_DATABASE"
    databaseStorageMode = StorageMode.REAL
}
