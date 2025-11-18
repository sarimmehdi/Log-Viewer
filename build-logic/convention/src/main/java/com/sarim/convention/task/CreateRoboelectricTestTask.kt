package com.sarim.convention.task

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

internal object CreateRoboelectricTestTask {
    const val TASK_NAME = "robolectricTest"
    const val TASK_DESCRIPTION = "Run Robolectric Compose tests"
    const val TASK_GROUP = "verification"

    fun Project.create() {
        tasks.register(TASK_NAME, Test::class.java) {
            description = TASK_DESCRIPTION
            group = TASK_GROUP

            val androidUnitTestTaskName = "testDebugUnitTest"

            testClassesDirs =
                project.files({
                    val testTask = project.tasks.findByName(androidUnitTestTaskName) as? Test
                    testTask?.testClassesDirs ?: project.files()
                })

            classpath =
                project.files({
                    val testTask = project.tasks.findByName(androidUnitTestTaskName) as? Test
                    testTask?.classpath ?: project.files()
                })

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
        }
    }
}
