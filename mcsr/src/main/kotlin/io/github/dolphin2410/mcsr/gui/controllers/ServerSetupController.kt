package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.api.util.ServerProgram
import io.github.dolphin2410.mcsr.api.util.data.MinecraftData
import io.github.dolphin2410.mcsr.api.util.data.PaperData
import io.github.dolphin2410.mcsr.api.util.data.SpigotData
import io.github.dolphin2410.mcsr.gui.AlertManager
import io.github.dolphin2410.mcsr.gui.SceneManager
import io.github.dolphin2410.mcsr.gui.StyleManager
import io.github.dolphin2410.mcsr.gui.util.ServerSetupMode
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.*

class ServerSetupController : ScriptGenerator() {
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

    override fun load(loader: FXMLLoader) {
        super.load(loader)
        acc.expandedPaneProperty().addListener { _, oldValue, newValue ->
            oldValue?.isCollapsible = true
            newValue?.let {
                Platform.runLater{
                    newValue.isCollapsible = false
                    mode = if (newValue == urlPane) ServerSetupMode.URL else ServerSetupMode.GUI
                }
            }
        }

        acc.expandedPaneProperty().set(guiPane)

        mode = ServerSetupMode.GUI

        server.items.setAll(*ServerProgram.values().map { it.str }.toTypedArray())
        server.selectionModel.selectFirst()
        updateVersions()
        updateBuilds()

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
    override fun next() {
        if (mode == ServerSetupMode.URL) {
            this.config.serverUrl.set(if (urlField.text == "") urlField.promptText else urlField.text)
            SceneManager.loadExtraSetup(config, true)
        } else if (mode == ServerSetupMode.GUI && version.selectionModel.selectedItem != null && server.selectionModel.selectedItem != null && build.selectionModel.selectedItem != null) {
            this.config.serverUrl.set(ServerProgram.from(server.selectionModel.selectedItem).data.buildUrl(version.selectionModel.selectedItem, build.selectionModel.selectedItem).toString())
            SceneManager.loadExtraSetup(config, true)
        } else {
            AlertManager.alert("Please select a valid option", ButtonType.OK) {
                StyleManager.style(dialogPane, "/style/alert.css")
            }
        }
    }

    @FXML
    override fun cancel() {
        super.cancel()
        SceneManager.loadHome()
    }

    override fun initialize() {
        super.initialize()
        urlField.promptText = "https://clip.aroxu.me/download?mc_version=${PaperData.latestVersion}"
    }
}