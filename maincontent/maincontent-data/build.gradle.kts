plugins {
    alias(libs.plugins.conventionPluginDataLibraryId)
}

extraModules {
    modules =
        listOf(":maincontent:maincontent-domain", ":sidebar:sidebar-sessions:sidebar-sessions-data")
}
