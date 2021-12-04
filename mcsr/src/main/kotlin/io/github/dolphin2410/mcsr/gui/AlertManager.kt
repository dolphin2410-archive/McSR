package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.MCSR
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.stage.StageStyle

object AlertManager {
    fun alert(title: String, vararg buttons: ButtonType, type: Alert.AlertType = Alert.AlertType.NONE, init: Alert.() -> Unit = {}): ButtonType {
        val alert = Alert(type, title, *buttons)
        alert.initOwner(MCSR.gui.stage)
        alert.initStyle(StageStyle.UNDECORATED)
        init(alert)
        return alert.showAndWait().get()
    }
}