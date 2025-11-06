plugins {
    alias(libs.plugins.conventionPluginDataLibraryId)
}

extraModules {
    modules = listOf(":sidebar:sidebar-dates:sidebar-dates-domain")
}
