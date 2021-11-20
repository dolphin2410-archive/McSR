import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("org.beryx.jlink") version "2.24.4"
}

// Java 16
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(16))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "16"
    }
}

group = "io.github.dolphin2410"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
}

application {
    mainClass.set("io.github.dolphin2410.mcsr.Main")
    mainModule.set("McSR.main")
}

javafx {
    modules = listOf(
        "javafx.controls", "javafx.fxml"
    )
}

jlink {
    launcher {
        name = "McSR"
    }
    forceMerge(
        "kotlin-stdlib",
        "kotlin-stdlib-common",
        "kotlin-stdlib-jdk7",
        "kotlin-stdlib-jdk8",
        "kotlinx-coroutines-core-jvm",
        "kotlinx-serialization-core-jvm",
        "kotlinx-serialization-json-jvm"
    )
    addExtraDependencies("javafx")
    imageZip.set(project.file("${project.buildDir}/${project.name}-bin.zip"))

    jpackage {
        installerName = "McSR Installer"
        installerOutputDir = File("C:/Users/dolph/Desktop/i")
    }
}

tasks {
    val packager = jpackage.get()

    create("windows") {
        packager.jpackageData.installerType = "msi"
        dependsOn(packager)
    }

    create("debian") {
        packager.jpackageData.installerType = "deb"
        dependsOn(packager)
    }

    create("redhat") {
        packager.jpackageData.installerType = "rpm"
        dependsOn(packager)
    }

    create("mac") {
        packager.jpackageData.installerType = "dmg"
        dependsOn(packager)
    }

    create("binary") {
        dependsOn(jlinkZip.get())
    }
}