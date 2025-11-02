plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules = listOf(":sidebar:sidebar-data", ":sidebar:sidebar-domain", ":sidebar:sidebar-presentation")
}
