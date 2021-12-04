package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.api.util.wrapper.StringArray
import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField

class ExtraSetupController: ScriptGenerator() {

    @FXML
    lateinit var folder: TextField

    @FXML
    lateinit var memory: TextField

    @FXML
    lateinit var autoBackup: CheckBox

    @FXML
    lateinit var autoRestart: CheckBox

    @FXML
    lateinit var jvmArgs: TextField

    @FXML
    override fun next() {
        val folderText = if (folder.text == "") "server" else folder.text
        val memoryText = if (memory.text == "") 1 else memory.text.toIntOrNull() ?: 1
        config.serverFolder.set(folderText)
        config.jvmArgs.set(StringArray(jvmArgs.text.split(" ").toTypedArray()))
        config.autoBackup.set(autoBackup.isSelected)
        config.autoReload.set(autoRestart.isSelected)
        config.memory.set(memoryText)
        SceneManager.loadScript(config, true)
    }

    @FXML
    override fun cancel() {
        SceneManager.loadServerSetup(config)
    }
}