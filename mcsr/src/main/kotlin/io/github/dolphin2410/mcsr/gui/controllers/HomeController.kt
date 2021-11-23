package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.Alert.AlertType
import javafx.scene.image.Image
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Circle
import javafx.scene.web.WebView
import javafx.stage.StageStyle


class HomeController: BaseController() {

    @FXML
    lateinit var credits: Button

    @FXML
    lateinit var list: ListView<String>

    @FXML
    lateinit var icon: Circle

    @FXML
    fun createNew() {
        SceneManager.loadServerSetup(true)
    }

    @FXML
    fun creditOpen() {
        Alert(
            AlertType.NONE
        ).apply {
            buttonTypes.add(ButtonType.OK)
            initStyle(StageStyle.UNDECORATED)
            initOwner(io.github.dolphin2410.mcsr.MCSR.gui.stage)
            dialogPane.content = WebView().apply {
                engine.loadContent(javaClass.classLoader.getResource("credit.html")!!.readText())
                setPrefSize(500.0, 230.0)
            }
            dialogPane.stylesheets.add(javaClass.classLoader.getResource("app.css")!!.toExternalForm())
            showAndWait()
        }
    }

    override fun initialize() {
        icon.fill = ImagePattern(Image(javaClass.classLoader.getResource("icon.png")!!.openStream()))
    }
}