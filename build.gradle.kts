import org.gradle.internal.os.OperatingSystem

plugins {
    kotlin("jvm") version "1.5.30"
    application
    id("org.openjfx.javafxplugin") version "0.0.10"
    id("org.beryx.jlink") version "2.24.4"
}

group = "io.github.dolphin2410"
version = "0.0.1"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
}

application {
    mainClass.set("io.github.dolphin2410.mcsr.Main")
    mainModule.set("McSR.main")
}

repositories {
    mavenCentral()
}

javafx {
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.swing")
}

jlink {
    launcher {
        name = "helloFX"
    }
    forceMerge("kotlin-stdlib", "kotlin-stdlib-common", "kotlin-stdlib-jdk7", "kotlin-stdlib-jdk8", "kotlinx-coroutines-core-jvm", "kotlinx-serialization-core-jvm", "kotlinx-serialization-json-jvm")
    addExtraDependencies("javafx")
    imageZip.set(project.file("${project.buildDir}/image-zip/hello-image.zip"))
}