package io.github.dolphin2410.mcsr.api.util

import io.github.dolphin2410.mcsr.api.config.config.saveConfig
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.parser.ConfigSerializer
import io.github.dolphin2410.mcsr.api.loader.MCSRLoader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.net.URI
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.jar.JarOutputStream

object ConfigurationBuilder {
    fun build(config: McSRConfig) {
        config.saveConfig()

        val configStream = ConfigSerializer.serialize(config)
        val bos = ByteArrayOutputStream()
        JarOutputStream(bos.apply(ResourceManager.stream(MCSRLoader.mcsr.javaClass, "/assets/runner.jar")::transferTo))
        // TODO handle without replacing
        Files.copy(ByteArrayInputStream(bos.toByteArray()), Paths.get(config.filename.get()), StandardCopyOption.REPLACE_EXISTING)

        val path = Paths.get(config.filename.get())
        FileSystems.newFileSystem(URI.create("jar:" + path.toUri()), hashMapOf("create" to "true")).use { fs ->
            Files.copy(ByteArrayInputStream(configStream.second.toByteArray()), fs.getPath("config.mcsrc"))
        }
    }
}