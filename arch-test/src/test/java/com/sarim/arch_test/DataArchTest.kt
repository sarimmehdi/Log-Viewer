package com.sarim.arch_test

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
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
internal class DataArchTest : BaseArchTest() {
    @ArchTest
    fun allowedClassesTest(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*data..")
            .and(validClasses)
            .and()
            .areTopLevelClasses()
            .should()
            .haveSimpleNameEndingWith("Dto")
            .orShould()
            .haveSimpleNameEndingWith("RepositoryImpl")
            .check(importedClasses)
    }

    @ArchTest
    fun packageDependencyTest(importedClasses: JavaClasses) {
        noClasses()
            .that()
            .resideInAPackage("..*data..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                "..*presentation..",
                "..*di..",
            ).check(importedClasses)
    }

    @ArchTest
    fun packageContainmentCheck(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*data..")
            .and()
            .haveSimpleNameEndingWith("Dto")
            .should()
            .resideInAPackage("..model..")
            .check(importedClasses)
        classes()
            .that()
            .resideInAPackage("..*data..")
            .and()
            .haveSimpleNameEndingWith("RepositoryImpl")
            .should()
            .resideInAPackage("..*data.repository..")
            .check(importedClasses)
    }

    @ArchTest
    fun layerChecks(importedClasses: JavaClasses) {
        layeredArchitecture()
            .consideringAllDependencies()
            .layer("Repository")
            .definedBy("..*data.repository..")
            .layer("Di")
            .definedBy("..*di..")
            .whereLayer("Repository")
            .mayOnlyBeAccessedByLayers("Di")
            .check(importedClasses)
    }

    @ArchTest
    fun inheritanceCheck(importedClasses: JavaClasses) {
        classes()
            .that()
            .resideInAPackage("..*data..")
            .and()
            .implement(
                object : DescribedPredicate<JavaClass>("an interface ending with 'Repository'") {
                    override fun test(input: JavaClass?): Boolean =
                        input != null && input.isInterface && input.simpleName.endsWith("Repository")
                },
            ).should()
            .haveSimpleNameEndingWith("RepositoryImpl")
            .check(importedClasses)
    }
}
