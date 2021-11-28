package io.github.dolphin2410.mcsr.api.plugin

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File
import java.net.URI
import java.net.URLClassLoader
import java.nio.file.FileSystems
import java.nio.file.Files

object PluginLoader {
    private val loadedPlugins = ArrayList<Plugin>()
    fun loadPlugin(file: File): Plugin {
        val loader = URLClassLoader(arrayOf(), PluginClassLoader())
        val plugin = FileSystems.newFileSystem(URI.create("jar:" + file.toPath().toUri()), hashMapOf<String, String>()).use { fs ->
            val lines = Files.readAllLines(fs.getPath("plugin.json"))
            val json = Json.parseToJsonElement(StringBuilder().apply {
                lines.forEach {
                    append(it)
                }
            }.toString())

            val mainClass = json.jsonObject["main"] ?: throw RuntimeException("Invalid 'plugin.json'. No 'main' field")

            Class.forName(mainClass.jsonPrimitive.content).getDeclaredConstructor().newInstance() as Plugin

        }

        loadedPlugins.add(plugin)

        plugin.initialize()

        return plugin
    }
}