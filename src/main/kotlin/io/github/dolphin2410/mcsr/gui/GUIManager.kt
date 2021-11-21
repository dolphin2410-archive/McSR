package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.MCSR
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import kotlinx.coroutines.coroutineScope

class GUIManager {

    lateinit var stage: Stage

    fun start() {
        SceneManager.loadHome()
        initStage()
        center()

        stage.show()
    }

    private fun initStage() {
        stage.initStyle(StageStyle.UNDECORATED)
        stage.width = 800.0
        stage.height = 500.0
        stage.isResizable = false
    }

    /**
     * Centers the JavaFX screen
     */
    private fun center() {
        val screen = Screen.getPrimary().visualBounds
        stage.x = (screen.width - stage.width) / 2
        stage.y = (screen.height - stage.height) / 2
    }

    fun close() {
        stage.close()
    }

    fun loadScene(scene: Scene) {
        stage.scene = scene
    }
}