package io.github.dolphin2410.mcsr

import io.github.dolphin2410.mcsr.api.util.ResourceManager
import javafx.application.Application
import javafx.scene.image.Image
import javafx.stage.Stage

class Main: Application() {
    override fun start(primaryStage: Stage) {
        MCSR.start(parameters.raw.toTypedArray())
        primaryStage.icons.add(Image(ResourceManager.stream(javaClass, "/assets/icon.png")))
        MCSR.gui.stage = primaryStage
        MCSR.gui.start()
    }
}