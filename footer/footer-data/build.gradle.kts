plugins {
    alias(libs.plugins.conventionPluginDataLibraryId)
}

extraModules {
    modules =
        listOf(":footer:footer-domain")
}
