import com.sarim.convention.utils.DependencyType

plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modulesWithType =
        listOf(
            ":sidebar:sidebar-sessions:sidebar-sessions-data" to DependencyType.IMPLEMENTATION,
            ":sidebar:sidebar-sessions:sidebar-sessions-domain" to DependencyType.IMPLEMENTATION,
            ":sidebar:sidebar-sessions:sidebar-sessions-presentation" to DependencyType.IMPLEMENTATION,
        )
}
