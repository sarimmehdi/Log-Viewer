plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modules = listOf(":sidebar:sidebar-dates:sidebar-dates-domain")
}
