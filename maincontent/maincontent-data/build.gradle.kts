import com.sarim.convention.utils.DependencyType

plugins {
    alias(libs.plugins.conventionPluginDataLibraryId)
}

extraModules {
    modulesWithType =
        listOf(
            ":maincontent:maincontent-domain" to DependencyType.API,
        )
}
