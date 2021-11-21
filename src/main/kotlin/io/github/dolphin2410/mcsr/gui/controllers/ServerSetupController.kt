package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import io.github.dolphin2410.mcsr.gui.util.ServerSetupMode
import io.github.dolphin2410.mcsr.util.data.MinecraftData
import io.github.dolphin2410.mcsr.util.data.PaperData
import io.github.dolphin2410.mcsr.util.data.SpigotData
import io.github.dolphin2410.mcsr.util.server.ServerProgram
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.Accordion
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.scene.control.TitledPane
import javafx.scene.input.MouseEvent

class ServerSetupController : BaseController() {
    @FXML
    lateinit var server: ComboBox<String>

    @FXML
    lateinit var version: ComboBox<String>

    @FXML
    lateinit var build: ComboBox<String>

    @FXML
    lateinit var acc: Accordion

    @FXML
    lateinit var guiPane: TitledPane

    @FXML
    lateinit var urlPane: TitledPane

    @FXML
    lateinit var urlField: TextField

    lateinit var mode: ServerSetupMode

    fun init() {
        acc.expandedPane = guiPane
        mode = ServerSetupMode.GUI

        acc.expandedPaneProperty().addListener { _, oldValue, newValue ->
            oldValue?.isCollapsible = true
            newValue?.let {
                Platform.runLater{
                    newValue.isCollapsible = false
                    mode = if (newValue == urlPane) ServerSetupMode.URL else ServerSetupMode.GUI
                    println(mode)
                }
            }
        }


        server.items.setAll(*ServerProgram.values().map { it.str }.toTypedArray())
        server.selectionModel.selectFirst()

        //https://launchermeta.mojang.com/mc/game/version_manifest.json
    }

    @FXML
    @Suppress("unchecked_cast")
    fun updateVersions() {
        if (server.selectionModel.selectedItem != null) {
            version.items.setAll(when (ServerProgram.from(server.selectionModel.selectedItem)) {
                ServerProgram.SPIGOT -> SpigotData.versions
                ServerProgram.PAPER -> PaperData.versions
                ServerProgram.VANILLA -> MinecraftData.versions
            })
        } else {
            version.items.clear()
            build.items.clear()
        }
        version.selectionModel.selectFirst()
    }

    @FXML
    fun updateBuilds() {
        if (version.selectionModel.selectedItem != null && server.selectionModel.selectedItem != null) {
            @Suppress("unchecked_cast")
            build.items.setAll(
                ServerProgram.from(server.selectionModel.selectedItem).data
                    .fetchBuilds(version.selectionModel.selectedItem))
        } else {
            build.items.clear()
        }
        build.selectionModel.selectFirst()
    }

    @FXML
    fun next() {
        if (mode == ServerSetupMode.URL && urlField.text != "") {
            SceneManager.loadExtraSetup()
        } else if (mode == ServerSetupMode.GUI && version.selectionModel.selectedItem != null && server.selectionModel.selectedItem != null && build.selectionModel.selectedItem != null) {
            SceneManager.loadExtraSetup()
        } else {
            println("Please input ")
        }
    }

    @FXML
    fun cancel() {
        SceneManager.loadHome()
    }
}