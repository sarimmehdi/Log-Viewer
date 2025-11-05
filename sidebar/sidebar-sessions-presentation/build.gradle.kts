plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modules = listOf(":sidebar:sidebar-sessions-domain")
}
