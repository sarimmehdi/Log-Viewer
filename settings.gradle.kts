includeBuild("build-logic")
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Log Viewer"
include(":app")
include(":utils")
include(":sidebar")
include(":nav")
include(":maincontent")
include(":maincontent:maincontent-di")
include(":maincontent:maincontent-data")
include(":maincontent:maincontent-domain")
include(":maincontent:maincontent-presentation")
include(":arch-test")
include(":sidebar:sidebar-dates-data")
include(":sidebar:sidebar-dates-domain")
include(":sidebar:sidebar-dates-di")
include(":sidebar:sidebar-dates-presentation")
include(":sidebar:sidebar-sessions-data")
include(":sidebar:sidebar-sessions-domain")
include(":sidebar:sidebar-sessions-di")
include(":sidebar:sidebar-sessions-presentation")
