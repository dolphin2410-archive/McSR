package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.MCSR
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import kotlinx.coroutines.coroutineScope

object SceneManager {
    private val home = Scene(FXMLLoader.load(javaClass.classLoader.getResource("home.fxml")))
    private val serverSetup = Scene(FXMLLoader.load(javaClass.classLoader.getResource("server_setup.fxml")))
    private val extraSetup = Scene(FXMLLoader.load(javaClass.classLoader.getResource("extra_setup.fxml")))
    private val script = Scene(FXMLLoader.load(javaClass.classLoader.getResource("script.fxml")))
    private val finish = Scene(FXMLLoader.load(javaClass.classLoader.getResource("finish.fxml")))

    fun loadHome() {
        MCSR.gui.loadScene(home)
    }

    fun loadServerSetup() {
        MCSR.gui.loadScene(serverSetup)
    }

    fun loadExtraSetup() {
        MCSR.gui.loadScene(extraSetup)
    }

    fun loadScript() {
        MCSR.gui.loadScene(script)
    }

    fun loadFinish() {
        MCSR.gui.loadScene(finish)
    }
}