package com.sarim.logviewer

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.lang.ArchCondition
import com.tngtech.archunit.lang.ConditionEvents
import com.tngtech.archunit.lang.SimpleConditionEvent
import kotlin.reflect.KVisibility

open class BaseArchTest {
    private val ignoreGeneratedClasses =
        object : DescribedPredicate<JavaClass>("ignore generated, R, and BuildConfig") {
            override fun test(p0: JavaClass?): Boolean =
                p0?.let {
                    val name = it.simpleName
                    !name.startsWith("R") &&
                        !name.contains("BuildConfig") &&
                        !name.endsWith("_Impl")
                } ?: false
        }

    private val ignoreSyntheticKotlinClasses =
        object : DescribedPredicate<JavaClass>("ignore synthetic Kotlin classes") {
            override fun test(p0: JavaClass?): Boolean =
                p0?.name?.let {
                    !it.endsWith("Kt") &&
                        !it.endsWith($$"$Creator") &&
                        !it.endsWith($$"$Companion") &&
                        !it.endsWith($$"R$plurals") &&
                        !it.endsWith($$"R$string")
                } ?: false
        }

    private fun Class<*>.isKotlinClass(): Boolean =
        this.declaredAnnotations.any {
            it.annotationClass.qualifiedName == "kotlin.Metadata"
        }

    private fun Class<*>.isKotlinInternal(): Boolean =
        runCatching {
            this.kotlin.visibility == KVisibility.INTERNAL
        }.getOrDefault(false)

    protected val validClasses =
        object : DescribedPredicate<JavaClass>("valid classes") {
            override fun test(p0: JavaClass?): Boolean =
                ignoreGeneratedClasses.test(p0) &&
                    ignoreSyntheticKotlinClasses.test(p0)
        }

    protected val beKotlinInternal: ArchCondition<JavaClass> =
        object : ArchCondition<JavaClass>("be Kotlin internal") {
            override fun check(
                javaClass: JavaClass,
                events: ConditionEvents,
            ) {
                val fqName = javaClass.name
                val ok =
                    runCatching {
                        val runtime = Class.forName(fqName)
                        if (!runtime.isKotlinClass()) {
                            events.add(
                                SimpleConditionEvent(
                                    javaClass,
                                    false,
                                    "$fqName is not a Kotlin class",
                                ),
                            )
                            return
                        }
                        if (!runtime.isKotlinInternal()) {
                            events.add(
                                SimpleConditionEvent(
                                    javaClass,
                                    false,
                                    "$fqName is not marked internal (kotlin visibility = ${runtime.kotlin.visibility})",
                                ),
                            )
                            return
                        }
                        // success
                        events.add(SimpleConditionEvent(javaClass, true, "$fqName is internal"))
                    }.onFailure { ex ->
                        events.add(
                            SimpleConditionEvent(
                                javaClass,
                                false,
                                "Could not load runtime class $fqName: ${ex.message}",
                            ),
                        )
                    }

                // force evaluation of runCatching to register events (above handles success/failure)
                ok.getOrNull()
            }
        }
}
