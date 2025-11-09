plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modules =
        listOf(
            ":maincontent:maincontent-domain",
            ":footer:footer-domain",
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
        )
}
