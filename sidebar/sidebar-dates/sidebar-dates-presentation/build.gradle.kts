import com.sarim.convention.utils.DependencyType

plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modulesWithType = listOf(":sidebar:sidebar-dates:sidebar-dates-domain" to DependencyType.API)
}
