package com.sarim.logviewer

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.runner.RunWith

@RunWith(ArchUnitRunner::class)
@AnalyzeClasses(
    packages = ["com.sarim"],
)
internal class DomainArchTest : BaseArchTest() {
    @ArchTest
    fun allowedClassesTest(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*domain..")
            .and()
            .resideOutsideOfPackage("..*domain.model..")
            .and(validClasses)
            .and()
            .areTopLevelClasses()
            .should()
            .haveSimpleNameEndingWith("Repository")
            .orShould()
            .haveSimpleNameEndingWith("UseCase")
            .check(importedClasses)
    }

    @ArchTest
    fun packageDependencyTest(importedClasses: JavaClasses) {
        noClasses()
            .that()
            .resideInAPackage("..*domain..")
            .and(validClasses)
            .should()
            .transitivelyDependOnClassesThat()
            .resideInAnyPackage(
                "..*presentation..",
                "..*data..",
                "..*di..",
            ).check(importedClasses)
    }

    @ArchTest
    fun classDependencyTest(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*domain.model..")
            .and(validClasses)
            .and()
            .areNotMemberClasses()
            .should()
            .transitivelyDependOnClassesThat()
            .resideInAnyPackage(
                "..*domain.repository..",
                "..*domain.usecase..",
                "..*data.repository..",
                "..*data.model..",
                "..*domain.model..",
                "..*presentation..",
            ).orShould()
            .transitivelyDependOnClassesThat()
            .haveSimpleNameEndingWith("ViewModel")
            .check(importedClasses)
    }

    @ArchTest
    fun packageContainmentCheck(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*domain..")
            .and(validClasses)
            .and()
            .haveSimpleNameEndingWith("UseCase")
            .should()
            .resideInAPackage("..usecase..")
            .check(importedClasses)
        classes()
            .that()
            .resideInAPackage("..*domain..")
            .and(validClasses)
            .and()
            .haveSimpleNameEndingWith("Repository")
            .should()
            .resideInAPackage("..*domain.repository..")
            .check(importedClasses)
    }

    @ArchTest
    fun layerChecks(importedClasses: JavaClasses) {
        layeredArchitecture()
            .consideringAllDependencies()
            .layer("Repository")
            .definedBy("..*domain.repository..")
            .layer("RepositoryImpl")
            .definedBy("..*data.repository..")
            .layer("Use Case")
            .definedBy("..*domain.usecase..")
            .layer("Di")
            .definedBy("..*di..")
            .whereLayer("Repository")
            .mayOnlyBeAccessedByLayers("Use Case", "Di", "RepositoryImpl")
            .check(importedClasses)
    }

    @ArchTest
    fun visibilityCheck(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*domain..")
            .and(validClasses)
            .and()
            .areTopLevelClasses()
            .should()
            .bePublic()
            .check(importedClasses)
    }
}
