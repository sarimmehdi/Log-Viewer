package com.sarim.convention.utils

enum class DependencyType(val typeName: String) {
    IMPLEMENTATION("implementation"),
    API("api")
}

open class AndroidExtraModulesConventionExtension {
    var modulesWithType: List<Pair<String, DependencyType>> = emptyList()
}
