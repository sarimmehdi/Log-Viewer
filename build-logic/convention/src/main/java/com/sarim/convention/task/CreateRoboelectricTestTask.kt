package com.sarim.convention.task

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

internal object CreateRoboelectricTestTask {
    const val TASK_NAME = "robolectricTest"
    const val TASK_DESCRIPTION = "Run Robolectric Compose tests"
    const val TASK_GROUP = "verification"

    fun Project.create() {
        pluginManager.apply("jacoco")
        tasks.register(TASK_NAME, Test::class.java) {
            description = TASK_DESCRIPTION
            group = TASK_GROUP

            val androidUnitTestTaskName = "testDebugUnitTest"
            val testTask = project.tasks.findByName(androidUnitTestTaskName) as? Test
            val dirs = testTask?.testClassesDirs?.files.orEmpty()
            if (dirs.isEmpty()) return@register

            testClassesDirs = project.files(dirs)
            classpath = project.files(testTask?.classpath ?: project.files())

            systemProperty("robolectric.logging", "stdout")

            val include = findProperty("robolectricInclude") as String?
            if (include != null) {
                filter { includeTestsMatching(include) }
            }

            useJUnit()
            testLogging {
                events("started", "passed", "skipped", "failed")
                showStandardStreams = true
            }
            extensions.configure(JacocoTaskExtension::class.java) {
                isIncludeNoLocationClasses = true
                excludes = listOf("jdk.internal.*")
            }
        }
    }
}

