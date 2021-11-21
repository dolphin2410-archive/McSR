package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.MCSR
import javafx.fxml.FXML

open class BaseController {
    @FXML
    fun exit() {
        MCSR.gui.close()
    }
}