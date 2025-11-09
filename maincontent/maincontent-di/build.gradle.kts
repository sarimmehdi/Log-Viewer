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
            ":footer:footer-data",
        )
}
