package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.CheckBox
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField

class ExtraSetupController: ScriptGenerator() {

    @FXML
    lateinit var folder: TextField

    @FXML
    lateinit var memory: TextField

    @FXML
    lateinit var memoryUnit: ComboBox<String>

    @FXML
    lateinit var autoBackup: CheckBox

    @FXML
    lateinit var autoRestart: CheckBox

    @FXML
    lateinit var jvmArgs: TextField

    @FXML
    override fun initialize() {
        memoryUnit.items.setAll("GB")
        super.initialize()
    }

    @FXML
    override fun next() {
        if (folder.text != "" && memory.text != "") {
            this.config.serverFolder.set(folder.text)
            this.config.jvmArgs.set(jvmArgs.text)
            this.config.autoBackup.set(autoBackup.isSelected)
            this.config.autoReload.set(autoRestart.isSelected)
            this.config.memory.set(memory.text.toIntOrNull() ?: 1)
            SceneManager.loadScript(this.config)
        } else {
            println("Please Input Folder and Memory")
        }
    }

    @FXML
    override fun cancel() {
        SceneManager.loadServerSetup(false, this.config)
    }
}