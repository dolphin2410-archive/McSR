package io.github.dolphin2410.mcsr.gui.controllers

import io.github.dolphin2410.mcsr.gui.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.TextField
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.net.URL
import java.util.*

class FinishController : ScriptGenerator() {
    @FXML
    lateinit var file: TextField

    @FXML
    lateinit var name: TextField

    override fun initialize() {
        file.promptText = "runner.jar"
        val api = "https://raw.githubusercontent.com/dariusk/corpora/master/data/words"
        val noun = Json.parseToJsonElement(URL("$api/nouns.json").readText()).jsonObject["nouns"]!!.jsonArray
        val adjective = Json.parseToJsonElement(URL("$api/adjs.json").readText()).jsonObject["adjs"]!!.jsonArray
        name.promptText = adjective[Random().nextInt(adjective.size)].jsonPrimitive.content + "-" + noun[Random().nextInt(noun.size)].jsonPrimitive.content
    }

    @FXML
    override fun next() {
        val filename = if (file.text != "") file.text else file.promptText
        val cfgName = if (name.text != "") name.text else name.promptText
        config.filename.set(filename)
        config.name.set(cfgName)
        config.hash.set(config.generateHash().joinToString())
        SceneManager.build(config)
    }

    @FXML
    override fun cancel() {
        SceneManager.loadScript(config)
    }
}