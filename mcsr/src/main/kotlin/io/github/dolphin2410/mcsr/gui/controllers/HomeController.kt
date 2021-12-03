package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.parser.ConfigSerializer
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import io.github.dolphin2410.mcsr.api.util.wrapper.PropertyObserver
import io.github.dolphin2410.mcsr.config.ConfigurationManager
import io.github.dolphin2410.mcsr.gui.SceneManager
import io.github.dolphin2410.mcsr.gui.components.ConfigurationBar
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.*
import javafx.scene.control.Alert.AlertType
import javafx.scene.image.Image
import javafx.scene.paint.ImagePattern
import javafx.scene.shape.Circle
import javafx.scene.web.WebView
import javafx.stage.FileChooser
import javafx.stage.StageStyle
import java.lang.module.Configuration


class HomeController: BaseController() {

    @FXML
    lateinit var credits: Button

    @FXML
    lateinit var list: ListView<Pair<String, McSRConfig>>

    @FXML
    lateinit var icon: Circle

    @FXML
    lateinit var load: Button

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
            initOwner(MCSR.gui.stage)
            dialogPane.content = WebView().apply {
                engine.loadContent(ResourceManager.resource(this@HomeController.javaClass, "/assets/credit.html").readText())
                setPrefSize(500.0, 230.0)
            }
            dialogPane.stylesheets.add(ResourceManager.resource(this@HomeController.javaClass, "/style/app.css").toExternalForm())
            dialogPane.styleClass.add("creditsAlert")
            showAndWait()
        }
    }

    override fun initialize() {
        icon.fill = ImagePattern(Image(ResourceManager.stream(javaClass, "/assets/icon.png")))
        load.setOnMouseClicked {
            FileChooser().apply {
                extensionFilters.addAll(
                    FileChooser.ExtensionFilter("McSRConfig", "*.mcsrc")
                )
            }.showOpenDialog(MCSR.gui.stage)?.let { file ->
                val config = ConfigSerializer.deserialize(file.inputStream())
                if (ConfigurationManager.map.data.any { entry -> entry.second.hash.get() == config.hash.get() }) {
                    Alert(AlertType.INFORMATION, "The Item with the same hash exists", ButtonType.OK).apply {
                        initOwner(MCSR.gui.stage)
                        initStyle(StageStyle.UNDECORATED)
                        show()
                    }
                } else {
                    ConfigurationManager.addConfig(file.nameWithoutExtension, config)
                }
            }
        }

        ConfigurationManager.loadConfig()

        ConfigurationManager.map.clearObservers()

        ConfigurationManager.map.addObserver(object: PropertyObserver<ArrayList<Pair<String, McSRConfig>>> {
            override fun accept(t: ArrayList<Pair<String, McSRConfig>>) {
                list.items.setAll(ConfigurationManager.map.data.map { it.first to it.second })
            }
        }, true)

        list.setCellFactory {
            ConfigurationBar()
        }
    }

    override fun load(loader: FXMLLoader) {
        super.load(loader)
        loader.getRoot<Parent>().stylesheets.add(ResourceManager.resource(javaClass, "/style/transparent.css").toExternalForm())
    }
}