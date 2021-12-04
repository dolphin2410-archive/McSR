javafx {
    version = "17"
    modules = listOf(
        "javafx.controls", "javafx.fxml", "javafx.web"
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
        "kotlinx-serialization-core-jvm",
        "kotlinx-serialization-json-jvm"
    )
    addExtraDependencies("javafx")
    imageZip.set(project.file("${project.buildDir}/${project.name}-bin.zip"))
    jpackage {
        installerName = "McSR Installer"
    }
}

application {
    mainClass.set("io.github.dolphin2410.mcsr.Main")
    mainModule.set("mcsr")
}