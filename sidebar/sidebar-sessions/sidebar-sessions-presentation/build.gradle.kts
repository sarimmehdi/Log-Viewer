plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modules =
        listOf(
            ":sidebar:sidebar-sessions:sidebar-sessions-domain",
            ":sidebar:sidebar-dates:sidebar-dates-domain",
        )
}
