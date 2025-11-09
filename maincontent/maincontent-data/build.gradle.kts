plugins {
    alias(libs.plugins.conventionPluginDataLibraryId)
}

extraModules {
    modules =
        listOf(
            ":maincontent:maincontent-domain",
            ":footer:footer-data",
            ":sidebar:sidebar-sessions:sidebar-sessions-data",
        )
}
