package io.github.dolphin2410.mcsr

import javafx.application.Application
import javafx.stage.Stage

class Main: Application() {
    override fun start(primaryStage: Stage) {
        MCSR.start(parameters.raw.toTypedArray())
        MCSR.gui.stage = primaryStage
        MCSR.gui.start()
    }
}