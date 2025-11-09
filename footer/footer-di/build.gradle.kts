plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":footer:footer-data",
            ":footer:footer-domain",
            ":footer:footer-presentation",
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
            ":maincontent:maincontent-domain",
        )
}
