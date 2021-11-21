package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML

class ExtraSetupController: BaseController() {
    @FXML
    fun next() {
        SceneManager.loadScript()
    }

    @FXML
    fun cancel() {
        SceneManager.loadServerSetup()
    }
}