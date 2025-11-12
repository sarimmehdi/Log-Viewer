plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modules =
        listOf(
            ":maincontent:maincontent-domain",
            ":footer:footer-domain",
            ":footer:footer-presentation",
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
        )
}
