package io.github.dolphin2410.mcsr.gui

import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.stage.Screen
import javafx.stage.Stage

class GUIManager {

    lateinit var stage: Stage

    fun start() {
        val parent = AnchorPane()
        val scene = Scene(parent)
        val label = Label("HELLO")
        parent.children.add(label)

        initStage(scene)
        center()

        stage.show()
    }

    private fun initStage(scene: Scene) {
        stage.scene = scene
        stage.width = 1920.0 / 2
        stage.height = 1080.0 / 2
        stage.isResizable = false
    }

    /**
     * Centers the JavaFX screen
     */
    fun center() {
        val screen = Screen.getPrimary().visualBounds
        stage.x = (screen.width - stage.width) / 2
        stage.y = (screen.height - stage.height) / 2
    }

    fun close() {

    }
}