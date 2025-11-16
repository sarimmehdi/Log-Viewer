package com.sarim.convention.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureModuleDependencies(modules: List<ModuleName>) {
    dependencies {
        modules.forEach { it.moduleType.moduleTypeName(project(it.name)) }
    }
}

internal enum class ModuleType(
    val moduleTypeName: String,
) {
    IMPLEMENTATION("implementation"),
    TEST_FIXTURES_API("testFixturesApi"),
}

internal data class ModuleName(
    val moduleType: ModuleType,
    val name: String,
)
