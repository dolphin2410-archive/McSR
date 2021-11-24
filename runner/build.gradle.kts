dependencies {
    shade(project(":api"))
    shade(kotlin("stdlib"))
    shade("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "io.github.dolphin2410.mcsr.runner.Main"
        }

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(configurations.shade.get().map {
            if(it.isDirectory) it else zipTree(it)
        })

        from(project.sourceSets["main"].output)

        copy {
            from(archiveFile.get().asFile)
            into(File(project(":mcsr").projectDir, "src/main/resources/assets/"))
        }

        println("JarTask...")
    }
}