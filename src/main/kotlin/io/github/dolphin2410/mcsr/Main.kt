package io.github.dolphin2410.mcsr

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class Main: Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.scene = Scene(AnchorPane())
        primaryStage.width = 1920.0 / 2
        primaryStage.height = 1080.0 / 2
        primaryStage.show()
    }
}