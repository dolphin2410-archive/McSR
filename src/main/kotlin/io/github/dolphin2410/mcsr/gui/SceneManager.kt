package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.gui.controllers.*
import javafx.fxml.FXMLLoader
import javafx.scene.Scene

object SceneManager {
    private val homeLoader = FXMLLoader()
    private val serverSetupLoader = FXMLLoader()
    private val extraSetupLoader = FXMLLoader()
    private val scriptLoader = FXMLLoader()
    private val finishLoader = FXMLLoader()

    private val home = Scene(homeLoader.load(javaClass.classLoader.getResource("home.fxml")!!.openStream()))
    private val serverSetup = Scene(serverSetupLoader.load(javaClass.classLoader.getResource("server_setup.fxml")!!.openStream()))
    private val extraSetup = Scene(extraSetupLoader.load(javaClass.classLoader.getResource("extra_setup.fxml")!!.openStream()))
    private val script = Scene(scriptLoader.load(javaClass.classLoader.getResource("script.fxml")!!.openStream()))
    private val finish = Scene(finishLoader.load(javaClass.classLoader.getResource("finish.fxml")!!.openStream()))

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