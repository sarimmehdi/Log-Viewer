import com.sarim.convention.utils.DependencyType

plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modulesWithType =
        listOf(
            ":sidebar:sidebar-dates:sidebar-dates-data" to DependencyType.IMPLEMENTATION,
            ":sidebar:sidebar-dates:sidebar-dates-domain" to DependencyType.IMPLEMENTATION,
            ":sidebar:sidebar-dates:sidebar-dates-presentation" to DependencyType.IMPLEMENTATION,
        )
}
