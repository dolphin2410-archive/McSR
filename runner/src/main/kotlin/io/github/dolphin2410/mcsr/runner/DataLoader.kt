package io.github.dolphin2410.mcsr.runner

import io.github.dolphin2410.mcsr.api.config.config.asEnum
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.mappers.AroxuMapper
import io.github.dolphin2410.mcsr.api.config.mappers.Dolphin2410Mapper
import io.github.dolphin2410.mcsr.api.config.parser.ConfigSerializer
import io.github.dolphin2410.mcsr.api.script.ScriptType
import io.github.dolphin2410.mcsr.api.util.OS
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipFile

object DataLoader {

    val processes = ArrayList<Process>()

    fun loadConfig(): McSRConfig {
        return ConfigSerializer.deserialize(
            ResourceManager.stream(javaClass, "config.mcsrc"))
    }

    fun printProc(process: Process) {
        val input = BufferedReader(InputStreamReader(process.inputStream))
        var line: String
        while (input.readLine().also { line = it } != null) {
            println(line)
        }
        input.close()
    }

    fun execute(script: URL, config: McSRConfig) {
        val folder = Files.createDirectories(Path.of(config.serverFolder.get()))
        val path = download(folder, script, config)

        when (config.serverSoftware.get().asEnum<ScriptType>()) {
            ScriptType.MONUN -> {
                val process = ProcessBuilder("wsl", "${path.toAbsolutePath()}", "launch")
                    .directory(folder.toFile())
                    .start()

                processes.add(process)

                printProc(process)

                process.waitFor()
            }

            ScriptType.DOLPHIN2410,
            ScriptType.AROXU -> {
                val process = ProcessBuilder("${path.toAbsolutePath()}")
                    .directory(folder.toFile())
                    .start()

                processes.add(process)

                printProc(process)

                process.waitFor()
            }

            null -> throw RuntimeException("Invalid Script Type")
        }
    }

    fun download(folder: Path, script: URL, config: McSRConfig): Path {
        return when (config.serverSoftware.get().asEnum<ScriptType>()) {
            ScriptType.DOLPHIN2410 -> {
                val binaryPath = Paths.get(folder.toString(), if (OS.get() == OS.WINDOWS) "server-script.exe" else "server-script")

                // Download from the internet
                Files.copy(script.openStream(), binaryPath)

                // Configuration File
                Files.copy(
                    config.toJson().toJson(Dolphin2410Mapper).toString().byteInputStream(),
                    Paths.get(folder.toString(), "server.conf.json")
                )

                binaryPath
            }
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