package com.sarim.logviewer

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass

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

    protected val validClasses =
        object : DescribedPredicate<JavaClass>("valid classes") {
            override fun test(p0: JavaClass?): Boolean =
                ignoreGeneratedClasses.test(p0) &&
                    ignoreSyntheticKotlinClasses.test(p0)
        }
}
