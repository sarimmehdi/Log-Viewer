plugins {
    alias(libs.plugins.conventionPluginDataLibraryId)
}

extraModules {
    modules = listOf(":sidebar:sidebar-sessions-domain")
}
