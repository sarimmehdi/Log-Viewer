plugins {
    alias(libs.plugins.conventionPluginDiLibraryId)
}

extraModules {
    modules =
        listOf(
            ":footer:footer-data",
            ":footer:footer-domain",
            ":footer:footer-presentation",
            ":maincontent:maincontent-domain",
        )
}
