plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":sidebar:sidebar-dates-data",
            ":sidebar:sidebar-dates-domain",
            ":sidebar:sidebar-dates-presentation",
        )
}
