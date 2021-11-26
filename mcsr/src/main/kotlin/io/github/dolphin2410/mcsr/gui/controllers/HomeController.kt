package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.parser.ConfigSerializer
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import io.github.dolphin2410.mcsr.api.util.wrapper.PropertyObserver
import io.github.dolphin2410.mcsr.config.ConfigurationManager
import io.github.dolphin2410.mcsr.gui.SceneManager
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
    lateinit var list: ListView<String>

    @FXML
    lateinit var icon: Circle

    @FXML
    lateinit var load: Button

    @FXML
    lateinit var dev: Button

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
                engine.loadContent(ResourceManager.resource(this@HomeController.javaClass, "/assets/credit.html").readText())
                setPrefSize(500.0, 230.0)
            }
            dialogPane.stylesheets.add(ResourceManager.resource(this@HomeController.javaClass, "/style/app.css").toExternalForm())
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
            }.showOpenDialog(MCSR.gui.stage)?.let {
                ConfigurationManager.addConfig(it.nameWithoutExtension, ConfigSerializer.deserialize(it.inputStream()))
            }
        }

        ConfigurationManager.loadConfig()

        dev.setOnMouseClicked {
            SceneManager.build(McSRConfig.of())
        }

        ConfigurationManager.map.clearObservers()

        ConfigurationManager.map.addObserver(object: PropertyObserver<ArrayList<Pair<String, McSRConfig>>> {
            override fun accept(t: ArrayList<Pair<String, McSRConfig>>) {
                list.items.setAll(ConfigurationManager.map.data.map { it.first })
            }
        })
    }

    override fun load(loader: FXMLLoader) {
        super.load(loader)
        loader.getRoot<Parent>().stylesheets.add(ResourceManager.resource(javaClass, "/style/transparent.css").toExternalForm())
    }
}