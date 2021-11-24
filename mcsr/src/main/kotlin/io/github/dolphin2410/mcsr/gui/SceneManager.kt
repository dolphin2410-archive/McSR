package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import io.github.dolphin2410.mcsr.gui.controllers.*
import javafx.fxml.FXMLLoader
import javafx.scene.Scene

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

    fun loadServerSetup(init: Boolean = false) {
        MCSR.gui.loadScene(serverSetup, serverSetupLoader)
        if (init) serverSetupLoader.getController<ServerSetupController>().init()

    }

    fun loadExtraSetup() {
        MCSR.gui.loadScene(extraSetup, extraSetupLoader)
    }

    fun loadScript() {
        MCSR.gui.loadScene(script, scriptLoader)
    }

    fun loadFinish() {
        MCSR.gui.loadScene(finish, finishLoader)
    }
}