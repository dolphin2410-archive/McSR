package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML

class FinishController : BaseController() {
    @FXML
    fun next() {
        SceneManager.loadHome()
    }

    @FXML
    fun cancel() {
        SceneManager.loadScript()
    }
}