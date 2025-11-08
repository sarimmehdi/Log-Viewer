plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modules =
        listOf(
            ":maincontent:maincontent-domain",
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
        )
}
