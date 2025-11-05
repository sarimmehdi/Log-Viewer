plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":sidebar:sidebar-sessions-data",
            ":sidebar:sidebar-sessions-domain",
            ":sidebar:sidebar-sessions-presentation",
        )
}
