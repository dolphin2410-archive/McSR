package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.config.ConfigurationManager
import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.TextField

class FinishController : ScriptGenerator() {
    @FXML
    lateinit var folder: TextField

    @FXML
    lateinit var name: TextField

    @FXML
    override fun next() {
        if (folder.text != "" && name.text != "") {
            this.config.serverFolder.set(folder.text)
            ConfigurationManager.addConfig(name.text, this.config)
            SceneManager.build(this.config)
        } else {
            println("Please Input")
        }
    }

    @FXML
    override fun cancel() {
        SceneManager.loadScript(this.config)
    }
}