import com.sarim.convention.utils.StorageMode

plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":footer:footer-data",
            ":footer:footer-domain",
            ":footer:footer-presentation",
        )
}

dataStorage {
    dataStorageName = "FOOTER_DTO_DATASTORE"
    dataStorageMode = StorageMode.REAL
}
