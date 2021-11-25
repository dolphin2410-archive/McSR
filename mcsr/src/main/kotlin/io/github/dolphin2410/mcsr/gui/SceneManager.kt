package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.parser.ConfigManager
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import io.github.dolphin2410.mcsr.gui.controllers.*
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarInputStream
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

object SceneManager {
    private val homeLoader = FXMLLoader()
    private val serverSetupLoader = FXMLLoader()
    private val extraSetupLoader = FXMLLoader()
    private val scriptLoader = FXMLLoader()
    private val finishLoader = FXMLLoader()

    private val home = Scene(homeLoader.load(ResourceManager.stream(javaClass, "/fxml/home.fxml")))
    private val serverSetup = Scene(serverSetupLoader.load(ResourceManager.stream(javaClass, "/fxml/server_setup.fxml")))
    private val extraSetup = Scene(extraSetupLoader.load(ResourceManager.stream(javaClass, "/fxml/extra_setup.fxml")))
    private val script = Scene(scriptLoader.load(ResourceManager.stream(javaClass, "/fxml/script.fxml")))
    private val finish = Scene(finishLoader.load(ResourceManager.stream(javaClass, "/fxml/finish.fxml")))

    fun loadHome() {
        MCSR.gui.loadScene(home, homeLoader)
    }

    fun loadServerSetup(init: Boolean = false, config: McSRConfig = McSRConfig.of()) {
        MCSR.gui.loadScene(serverSetup, serverSetupLoader)
        if (init) serverSetupLoader.getController<ServerSetupController>().init()
        serverSetupLoader.getController<ScriptGenerator>().config = config

    }

    fun loadExtraSetup(config: McSRConfig) {
        MCSR.gui.loadScene(extraSetup, extraSetupLoader)
        extraSetupLoader.getController<ScriptGenerator>().config = config
    }

    fun loadScript(config: McSRConfig) {
        MCSR.gui.loadScene(script, scriptLoader)
        scriptLoader.getController<ScriptGenerator>().config = config
    }

    fun loadFinish(config: McSRConfig) {
        MCSR.gui.loadScene(finish, finishLoader)
        finishLoader.getController<ScriptGenerator>().config = config
    }

    fun build(config: McSRConfig) {
        val configStream = ConfigManager.serialize(config)
        val bos = ByteArrayOutputStream()
        val jarStream = JarOutputStream(bos.apply(ResourceManager.stream(javaClass, "/assets/runner.jar")::transferTo))
        jarStream.putNextEntry(JarEntry("config.mcsrc"))
        configStream.second.writeTo(jarStream)
        Files.copy(ByteArrayInputStream(bos.toByteArray()), Paths.get("./my.jar"))

        MCSR.gui.loadScene(home, homeLoader)
    }
}