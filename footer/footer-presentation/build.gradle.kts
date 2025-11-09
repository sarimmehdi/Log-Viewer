plugins {
    alias(libs.plugins.conventionPluginPresentationLibraryId)
}

extraModules {
    modules =
        listOf(
            ":footer:footer-domain",
            ":maincontent:maincontent-domain",
        )
}
