import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    application
}

group = "io.github.dolphin2410"
version = "0.0.1-Beta"

repositories {
    mavenCentral()
}

configurations {
    create("shade")
}

dependencies {
    implementation(kotlin("stdlib"))
    configurations["shade"]("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    configurations["shade"]("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "16"
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(16))
}

dependencies {
    configurations["shade"].forEach {
        implementation(files(it))
    }
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "io.github.dolphin2410.mcsr.MCSR"
        }

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(configurations["shade"].map {
            if (it.isDirectory) it else zipTree(it)
        })
    }
}