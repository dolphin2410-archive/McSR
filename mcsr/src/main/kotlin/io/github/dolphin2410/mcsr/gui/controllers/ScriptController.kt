package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.api.script.ScriptType
import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.shape.Circle

class ScriptController : ScriptGenerator() {

    @FXML
    lateinit var nextScript: Button

    @FXML
    lateinit var prevScript: Button

    @FXML
    lateinit var profile: Circle

    @FXML
    lateinit var scriptName: Label

    private var scriptType = ScriptType.AROXU

    private val scripts = ScriptType.values()

    fun update() {
        val isLast = scripts.indexOf(scriptType) == scripts.size - 1

        val isFirst = scripts.indexOf(scriptType) == 0

        nextScript.isVisible = !isLast
        prevScript.isVisible = !isFirst

        scriptName.text = scriptType.name.lowercase().replaceFirstChar { it.uppercase() }
    }

    @FXML
    override fun initialize() {
        update()
        nextScript.setOnMouseClicked {
            scriptType = scripts[scripts.indexOf(scriptType) + 1]
            update()
        }

        prevScript.setOnMouseClicked {
            scriptType = scripts[scripts.indexOf(scriptType) - 1]
            update()
        }
        super.initialize()
    }

    @FXML
    override fun next() {
        config.serverSoftware.set(scriptType.name)
        SceneManager.loadFinish(config)
    }

    @FXML
    override fun cancel() {
        SceneManager.loadExtraSetup(this.config)
    }
}