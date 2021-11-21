package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML

class ServerSetupController : BaseController() {
    @FXML
    fun next() {
        SceneManager.loadExtraSetup()
    }

    @FXML
    fun cancel() {
        SceneManager.loadHome()
    }
}