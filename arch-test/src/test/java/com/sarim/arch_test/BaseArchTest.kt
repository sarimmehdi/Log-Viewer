package com.sarim.arch_test

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass

open class BaseArchTest {
    private val ignoreGeneratedClasses =
        object : DescribedPredicate<JavaClass>("ignore R and BuildConfig") {
            override fun test(p0: JavaClass?): Boolean =
                p0?.let { !it.simpleName.startsWith("R") && !it.simpleName.contains("BuildConfig") }
                    ?: false
        }

    private val ignoreSyntheticKotlinClasses =
        object : DescribedPredicate<JavaClass>("ignore synthetic Kotlin classes") {
            override fun test(p0: JavaClass?): Boolean =
                p0?.name?.let {
                    !it.endsWith("Kt") && !it.endsWith($$"$Creator") && !it.endsWith($$"$Companion")
                } ?: false
        }

    protected val validClasses =
        object : DescribedPredicate<JavaClass>("valid classes") {
            override fun test(p0: JavaClass?): Boolean =
                ignoreGeneratedClasses.test(p0) &&
                    ignoreSyntheticKotlinClasses.test(p0)
        }
}
