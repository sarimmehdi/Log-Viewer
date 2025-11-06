import com.sarim.convention.utils.DependencyType

plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modulesWithType = listOf(":sidebar:sidebar-sessions:sidebar-sessions-domain" to DependencyType.API)
}
