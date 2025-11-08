package com.sarim.logviewer

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
    fun allowedClassesTest(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*presentation..")
            .and(validClasses)
            .and()
            .areTopLevelClasses()
            .should()
            .haveSimpleNameEndingWith("Screen")
            .orShould()
            .haveSimpleNameEndingWith("ScreenState")
            .orShould()
            .haveSimpleNameEndingWith("ScreenToViewModelEvents")
            .orShould()
            .haveSimpleNameEndingWith("ScreenUseCases")
            .orShould()
            .haveSimpleNameEndingWith("ScreenViewModel")
            .orShould()
            .haveSimpleNameEndingWith("Component")
            .orShould()
            .haveSimpleNameEndingWith("ScreenData")
            .orShould()
            .haveSimpleNameEndingWith("ScreenDataParameterProvider")
            .orShould()
            .haveSimpleNameEndingWith("ComponentData")
            .orShould()
            .haveSimpleNameEndingWith("ComponentDataParameterProvider")
            .orShould()
            .haveSimpleNameEndingWith("Preview")
            .check(importedClasses)
    }

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
            .and()
            .areNotInterfaces()
            .and()
            .haveSimpleNameNotEndingWith("DataParameterProvider")
            .should()
            .transitivelyDependOnClassesThat()
            .resideInAnyPackage(
                "..*domain..",
                "..*presentation..",
                "..*di..",
                "..*nav..",
            ).check(importedClasses)
        classes()
            .that()
            .haveSimpleNameEndingWith("ScreenViewModel")
            .should()
            .transitivelyDependOnClassesThat()
            .haveSimpleNameEndingWith("ScreenState")
            .orShould()
            .haveSimpleNameEndingWith("ScreenToViewModelEvents")
            .orShould()
            .haveSimpleNameEndingWith("ScreenUseCases")
            .check(importedClasses)
    }

    @ArchTest
    fun visibilityCheck(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*presentation..")
            .and(validClasses)
            .and()
            .areTopLevelClasses()
            .and()
            .haveSimpleNameEndingWith("Screen")
            .or()
            .haveSimpleNameEndingWith("ScreenState")
            .or()
            .haveSimpleNameEndingWith("ScreenToViewModelEvents")
            .or()
            .haveSimpleNameEndingWith("ScreenUseCases")
            .or()
            .haveSimpleNameEndingWith("ScreenViewModel")
            .should()
            .bePublic()
            .check(importedClasses)

        classes()
            .that()
            .resideInAPackage("..*presentation..")
            .and(validClasses)
            .and()
            .areTopLevelClasses()
            .and()
            .haveSimpleNameEndingWith("Component")
            .should()
            .bePackagePrivate()
            .check(importedClasses)

        classes()
            .that()
            .resideInAPackage("..*presentation..")
            .and(validClasses)
            .and()
            .areTopLevelClasses()
            .and()
            .haveSimpleNameEndingWith("Preview")
            .should()
            .bePrivate()
            .check(importedClasses)
    }
}
