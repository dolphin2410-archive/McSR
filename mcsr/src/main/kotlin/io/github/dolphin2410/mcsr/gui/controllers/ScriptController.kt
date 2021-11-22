package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML

class ScriptController : BaseController() {
    @FXML
    fun next() {
        SceneManager.loadFinish()
    }

    @FXML
    fun cancel() {
        SceneManager.loadExtraSetup()
    }
}