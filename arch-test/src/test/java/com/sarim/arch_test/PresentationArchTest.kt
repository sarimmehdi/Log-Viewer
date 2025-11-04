package com.sarim.arch_test

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.runner.RunWith

@RunWith(ArchUnitRunner::class)
@AnalyzeClasses(
    packages = ["com.sarim"],
)
internal class PresentationArchTest : BaseArchTest() {
    @ArchTest
    fun packageDependencyTest(importedClasses: JavaClasses) {
        noClasses()
            .that()
            .resideInAPackage("..*presentation..")
            .and(validClasses)
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                "..*data..",
                "..*di..",
            ).check(importedClasses)
    }

    @ArchTest
    fun classDependencyTest(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*presentation..")
            .and(validClasses)
            .should()
            .onlyHaveDependentClassesThat()
            .resideInAnyPackage(
                "..*domain..",
                "..*presentation..",
                "..*di..",
            ).check(importedClasses)
        classes()
            .that()
            .haveSimpleNameEndingWith("ScreenState")
            .should()
            .onlyHaveDependentClassesThat()
            .haveSimpleNameEndingWith("ScreenViewModel")
        classes()
            .that()
            .haveSimpleNameEndingWith("ScreenToViewModelEvents")
            .should()
            .onlyHaveDependentClassesThat()
            .haveSimpleNameEndingWith("ScreenViewModel")
        classes()
            .that()
            .haveSimpleNameEndingWith("ScreenUseCases")
            .should()
            .onlyHaveDependentClassesThat()
            .haveSimpleNameEndingWith("ScreenViewModel")
    }
}
