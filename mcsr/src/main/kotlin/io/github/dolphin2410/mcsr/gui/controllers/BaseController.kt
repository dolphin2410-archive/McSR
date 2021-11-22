package io.github.dolphin2410.mcsr.gui.controllers

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.layout.Pane

open class BaseController {

    @FXML
    lateinit var closeBtn: Button

    @FXML
    fun exit() {
        io.github.dolphin2410.mcsr.MCSR.gui.close()
    }

    @FXML
    open fun initialize() {

    }

    open fun load(loader: FXMLLoader) {
        loader.getRoot<Pane>().stylesheets.add(javaClass.classLoader.getResource("app.css")!!.toExternalForm())
    }
}