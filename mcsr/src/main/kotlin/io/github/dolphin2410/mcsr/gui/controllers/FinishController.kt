package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.config.ConfigurationManager
import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.TextField
import java.security.MessageDigest
import kotlin.random.Random

class FinishController : ScriptGenerator() {
    @FXML
    lateinit var file: TextField

    @FXML
    lateinit var name: TextField

    @FXML
    override fun next() {
        if (file.text != "" && name.text != "") {
            this.config.filename.set(file.text)
            this.config.name.set(name.text)
            this.config.hash.set(config.generateHash().joinToString())
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