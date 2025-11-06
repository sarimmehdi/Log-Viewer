package com.sarim.convention.utils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureModuleDependencies(modules: List<Pair<String, DependencyType>>) {
    dependencies {
        modules.forEach { it.second.typeName(project(it.first)) }
    }
}
