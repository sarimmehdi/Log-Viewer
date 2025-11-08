plugins {
    alias(libs.plugins.conventionPluginDataLibraryId)
}

extraModules {
    modules =
        listOf(
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
            ":sidebar:sidebar-dates:sidebar-dates-data",
        )
}
