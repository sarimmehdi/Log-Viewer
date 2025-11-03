import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.androidApplicationPlugin) apply false
    alias(libs.plugins.kotlinAndroidPlugin) apply false
    alias(libs.plugins.kotlinComposePlugin) apply false
    alias(libs.plugins.ktlintPlugin) apply false
    alias(libs.plugins.detektPlugin) apply false
    alias(libs.plugins.androidLibraryPlugin) apply false
}

subprojects {
    pluginManager.apply(
        rootProject.libs.plugins.ktlintPlugin
            .get()
            .pluginId,
    )
    pluginManager.apply(
        rootProject.libs.plugins.detektPlugin
            .get()
            .pluginId,
    )

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        android = true
        ignoreFailures = false
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
            reporter(ReporterType.SARIF)
        }
    }

    configure<DetektExtension> {
        config.setFrom("${project.rootDir}/detekt.yml")
        parallel = true
    }
}
