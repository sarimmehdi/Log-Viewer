plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":sidebar:sidebar-dates:sidebar-dates-data",
            ":sidebar:sidebar-sessions:sidebar-sessions-data",
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
            ":sidebar:sidebar-sessions:sidebar-sessions-presentation",
        )
}
