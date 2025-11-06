import com.sarim.convention.utils.DependencyType

plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modulesWithType =
        listOf(
            ":maincontent:maincontent-data" to DependencyType.IMPLEMENTATION,
            ":maincontent:maincontent-domain" to DependencyType.IMPLEMENTATION,
            ":maincontent:maincontent-presentation" to DependencyType.IMPLEMENTATION,
        )
}
