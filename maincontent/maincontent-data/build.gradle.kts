plugins {
    alias(libs.plugins.conventionPluginDataLibraryId)
}

extraModules {
    modules = listOf(":maincontent:maincontent-domain")
}
