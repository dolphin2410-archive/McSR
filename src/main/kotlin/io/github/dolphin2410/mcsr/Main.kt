package io.github.dolphin2410.mcsr

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class Main: Application() {
    override fun start(primaryStage: Stage) {
        MCSR.start()
        MCSR.gui.stage = primaryStage
        MCSR.gui.start()
    }
}