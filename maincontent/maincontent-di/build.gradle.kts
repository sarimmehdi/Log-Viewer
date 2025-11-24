import com.sarim.convention.utils.StorageMode

plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":maincontent:maincontent-data",
            ":maincontent:maincontent-domain",
            ":maincontent:maincontent-presentation",
            ":sidebar:sidebar-sessions:sidebar-sessions-data",
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
            ":footer:footer-data",
            ":footer:footer-domain",
            ":footer:footer-presentation",
        )
}

dataStorage {
    databaseName = "LOG_MESSAGE_DTO_DATABASE"
    databaseStorageMode = StorageMode.REAL
}
