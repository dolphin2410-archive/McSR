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

        from(project.sourceSets["main"].output)
        from(configurations.shade.get().map {
            from (if(it.isDirectory) it else zipTree(it))
        })

        archiveFileName.set("runner.jar")
        destinationDirectory.set(File(project.rootDir, "mcsr/src/main/resources"))
    }
}