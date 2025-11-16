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
include(":sidebar:sidebar-dates:sidebar-dates-data")
include(":sidebar:sidebar-dates:sidebar-dates-domain")
include(":sidebar:sidebar-dates:sidebar-dates-di")
include(":sidebar:sidebar-dates:sidebar-dates-presentation")
include(":sidebar:sidebar-sessions:sidebar-sessions-data")
include(":sidebar:sidebar-sessions:sidebar-sessions-domain")
include(":sidebar:sidebar-sessions:sidebar-sessions-di")
include(":sidebar:sidebar-sessions:sidebar-sessions-presentation")
include(":sidebar:sidebar-dates")
include(":sidebar:sidebar-sessions")
include(":footer")
include(":footer:footer-data")
include(":footer:footer-domain")
include(":footer:footer-presentation")
include(":footer:footer-di")
include(":header")
include(":header:header-presentation")
include(":ui")
