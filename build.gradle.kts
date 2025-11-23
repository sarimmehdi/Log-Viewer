import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.androidApplicationPlugin) apply false
    alias(libs.plugins.kotlinAndroidPlugin) apply false
    alias(libs.plugins.kotlinComposePlugin) apply false
    alias(libs.plugins.ktlintPlugin) apply false
    alias(libs.plugins.detektPlugin) apply false
    alias(libs.plugins.androidLibraryPlugin) apply false
    alias(libs.plugins.kspPlugin) apply false
    alias(libs.plugins.moduleGraphPlugin)
    alias(libs.plugins.sonarPlugin)
    alias(libs.plugins.conventionPluginJacocoId)
}

val excludedForCoverage = setOf("app", "utils", "nav")

val nonCoverageModules =
    subprojects
        .filter { it.name in excludedForCoverage }
        .map { it.name }

val sonarExclusionString =
    nonCoverageModules.joinToString(",") { moduleName -> "**/$moduleName/**" }

sonar {
    properties {
        property("sonar.coverage.exclusions", sonarExclusionString)
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${project.rootDir}/build/reports/jacoco/jacocoAggregatedReport/jacocoAggregatedReport.xml",
        )
        property("sonar.projectKey", "muhammad-sarim-mehdi_log-viewer")
        property("sonar.organization", "muhammad-sarim-mehdi")
        property(
            "sonar.coverage.exclusions",
            listOf(
                "**/Preview**",
                "**/*Preview*",
                "**/*Preview",
                "**/*PreviewKt*",
                "**/*PreviewData*",
                "**/*ParameterProvider*",
                "**/ComposableSingletons*",
                "**/*SnackbarController*",
                "**/*SnackbarEvent*",
                "**/*SnackbarAction*",
                "**/*DtoSerializer*",
                "**/*RepositoryImpl*",
                "**/*Dao*",
                "**/*DtoFts",
                "**/*Database",
                "**/*Database*",
                "**/*_di/**",
                "**/ui/theme/**",
                "**/utils/**",
            ).joinToString(","),
        )
    }
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

    configure<KtlintExtension> {
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
        ignoreFailures = false
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(true)
            xml.required.set(true)
            txt.required.set(true)
            sarif.required.set(true)
        }
    }
}
