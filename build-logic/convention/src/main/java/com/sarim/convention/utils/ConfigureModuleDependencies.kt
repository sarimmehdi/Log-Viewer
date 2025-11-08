package com.sarim.convention.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureModuleDependencies(modules: List<String>) {
    dependencies {
        modules.forEach { "implementation"(project(it)) }
    }
}
