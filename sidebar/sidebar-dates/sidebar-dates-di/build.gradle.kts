plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":sidebar:sidebar-dates:sidebar-dates-data",
            ":sidebar:sidebar-dates:sidebar-dates-domain",
            ":sidebar:sidebar-dates:sidebar-dates-presentation",
        )
}
