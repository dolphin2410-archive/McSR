package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.script.ScriptType
import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.shape.Circle
import javafx.stage.StageStyle
import java.awt.Desktop
import java.net.URI

class ScriptController : ScriptGenerator() {

    @FXML
    lateinit var aroxu: Circle

    @FXML
    lateinit var monun: Circle

    @FXML
    lateinit var add: Circle

    lateinit var selected: ScriptType

    @FXML
    override fun initialize() {
        monun.setOnMouseClicked {
            selected = ScriptType.MONUN
        }

        aroxu.setOnMouseClicked {
            selected = ScriptType.AROXU
        }

        add.setOnMouseClicked {
            val alert = Alert(Alert.AlertType.INFORMATION, "If you want me to support your server-script, please create an issue on github", ButtonType.OK, ButtonType.CANCEL)
            alert.initStyle(StageStyle.UNDECORATED)
            alert.initOwner(MCSR.gui.stage)
            when (alert.showAndWait().get()) {
                ButtonType.OK -> Desktop.getDesktop().browse(URI.create("https://github.com/dolphin2410/McSR/issues/new"))
            }
        }
        super.initialize()
    }

    @FXML
    override fun next() {
        if (::selected.isInitialized) {
            this.config.serverSoftware.set(selected.name)
            SceneManager.loadFinish(this.config)
        } else {
            println("Please...")
        }
    }

    @FXML
    override fun cancel() {
        SceneManager.loadExtraSetup(this.config)
    }
}