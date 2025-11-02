import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.sarim.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
}

dependencies {
    implementation(libs.androidGradlePluginLibrary)
    implementation(libs.kotlinGradlePluginLibrary)
    implementation(libs.kotlinSerializationPluginLibrary)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        create(libs.versions.conventionPluginAndroidApplicationName.get()) {
            id = libs.plugins.conventionPluginAndroidApplicationId.get().pluginId
            implementationClass = libs.versions.conventionPluginAndroidApplicationClass.get()
        }
    }
    plugins {
        create(libs.versions.conventionPluginDiLibraryName.get()) {
            id = libs.plugins.conventionPluginDiLibraryId.get().pluginId
            implementationClass = libs.versions.conventionPluginDiLibraryClass.get()
        }
    }
    plugins {
        create(libs.versions.conventionPluginDataLibraryName.get()) {
            id = libs.plugins.conventionPluginDataLibraryId.get().pluginId
            implementationClass = libs.versions.conventionPluginDataLibraryClass.get()
        }
    }
    plugins {
        create(libs.versions.conventionPluginDomainLibraryName.get()) {
            id = libs.plugins.conventionPluginDomainLibraryId.get().pluginId
            implementationClass = libs.versions.conventionPluginDomainLibraryClass.get()
        }
    }
    plugins {
        create(libs.versions.conventionPluginPresentationLibraryName.get()) {
            id = libs.plugins.conventionPluginPresentationLibraryId.get().pluginId
            implementationClass = libs.versions.conventionPluginPresentationLibraryClass.get()
        }
    }
    plugins {
        create(libs.versions.conventionPluginNavLibraryName.get()) {
            id = libs.plugins.conventionPluginNavLibraryId.get().pluginId
            implementationClass = libs.versions.conventionPluginNavLibraryClass.get()
        }
    }
    plugins {
        create(libs.versions.conventionPluginUtilsLibraryName.get()) {
            id = libs.plugins.conventionPluginUtilsLibraryId.get().pluginId
            implementationClass = libs.versions.conventionPluginUtilsLibraryClass.get()
        }
    }
}