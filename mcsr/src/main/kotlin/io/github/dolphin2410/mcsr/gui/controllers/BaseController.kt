package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Button

open class BaseController {

    lateinit var loader: FXMLLoader

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
        loader.getRoot<Parent>().stylesheets.add(ResourceManager.resource(javaClass, "/style/app.css").toExternalForm())
        this.loader = loader
    }
}