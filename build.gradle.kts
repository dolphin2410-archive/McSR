import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    kotlin("jvm") version "1.6.0"
    application
    id("org.openjfx.javafxplugin") version "0.0.10" apply false
    id("org.beryx.jlink") version "2.24.4"
    id("org.javamodularity.moduleplugin") version "1.8.10" apply false
}

// Java 16
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(16))
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "application")
    apply(plugin = "org.javamodularity.moduleplugin")

    configurations {
        create("shade")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
        if (project.name != "api") {
            implementation(project(":api"))
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "16"
        }
    }
}

group = "io.github.dolphin2410"
version = "0.0.1"

repositories {
    mavenCentral()
}

project(":mcsr") {
    apply(plugin = "application")
    apply(plugin = "org.openjfx.javafxplugin")
    apply(plugin = "org.beryx.jlink")
}

tasks {
    val runner = create<Jar>("runner") {
        dependsOn(project(":runner").tasks.jar)
    }

    register<DefaultTask>("binary") {
        dependsOn(runner)
        dependsOn(project(":mcsr").tasks.jlinkZip)
    }

    named<JavaExec>("run") {
        dependsOn(runner)
        dependsOn(project(":mcsr").tasks.run)
    }
}