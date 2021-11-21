package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.MCSR
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*

open class BaseController {

    @FXML
    lateinit var closeBtn: Button

    @FXML
    fun exit() {
        MCSR.gui.close()
    }

    @FXML
    open fun initialize() {

    }

    open fun load(loader: FXMLLoader) {
        loader.getRoot<Pane>().stylesheets.add(javaClass.classLoader.getResource("app.css")!!.toExternalForm())
    }
}