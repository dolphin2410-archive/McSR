package io.github.dolphin2410.mcsr.runner

import io.github.dolphin2410.mcsr.api.config.config.asEnum
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.mappers.AroxuMapper
import io.github.dolphin2410.mcsr.api.config.parser.ConfigManager
import io.github.dolphin2410.mcsr.api.script.ScriptType
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import java.io.FileNotFoundException
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipFile

object DataLoader {
    fun loadConfig(): McSRConfig {
        return ConfigManager.deserialize(
            ResourceManager.stream(javaClass, "config.mcsrc"))
    }

    fun execute(script: URL, config: McSRConfig) {
        val folder = Files.createDirectories(Path.of(config.serverFolder.get()))
        val path = download(folder, script, config)

        when (config.serverSoftware.get().asEnum<ScriptType>()) {
            ScriptType.MONUN -> {
                val process = ProcessBuilder("wsl", "${path.toAbsolutePath()}", "launch")
                    .directory(folder.toFile())
                    .start()

                process.waitFor()
            }

            ScriptType.AROXU -> {
                val process = ProcessBuilder("${path.toAbsolutePath()}")
                    .directory(folder.toFile())
                    .start()

                process.waitFor()
            }

            null -> throw RuntimeException("Invalid Script Type")
        }
    }

    fun download(folder: Path, script: URL, config: McSRConfig): Path {
        return when (config.serverSoftware.get().asEnum<ScriptType>()) {
            ScriptType.AROXU -> {
                val zipFilePath = Paths.get(folder.toString(), "server.zip")

                // Download from the internet
                Files.copy(script.openStream(), zipFilePath)

                // To ZipFile
                val zipFile = ZipFile(zipFilePath.toString())

                // Get first element
                val executable = zipFile.entries().nextElement()

                // Get InputStream
                val executableInputStream = zipFile.getInputStream(executable)

                // Path
                val executablePath = Paths.get(folder.toString(), executable.name)

                // Load to executable
                Files.copy(executableInputStream, executablePath)

                // Close ZipFile
                zipFile.close()

                // Delete ZipFile
                Files.deleteIfExists(zipFilePath)

                // Configuration File
                Files.copy(
                    config.toJson().toJson(AroxuMapper).toString().byteInputStream(),
                    Paths.get(folder.toString(), "server.conf.json")
                )

                executablePath
            }

            ScriptType.MONUN -> {
                val filePath = Paths.get(folder.toString(), "server.sh")

                Files.copy(script.openStream(), filePath)

                Files.copy(
                    config.toConf().toString().byteInputStream(),
                    Paths.get(folder.toString(), "server.conf")
                )

                filePath
            }

            null -> {
                throw RuntimeException("Invalid Script Type")
            }
        }
    }
}