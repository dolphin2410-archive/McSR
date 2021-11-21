package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML

class HomeController: BaseController() {
    @FXML
    fun createNew() {
        SceneManager.loadServerSetup()
    }
}