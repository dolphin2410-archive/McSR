package io.github.dolphin2410.mcsr.gui.components

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.parser.ConfigSerializer
import io.github.dolphin2410.mcsr.api.util.ConfigurationBuilder
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority
import javafx.stage.StageStyle
import java.io.ByteArrayInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

class ConfigurationBar: ListCell<Pair<String, McSRConfig>>() {
    private val box = HBox()
    private val label = Label()
    private val button = Button("Share")
    private val pane = Pane()
    private lateinit var config: McSRConfig

    init {
        box.children.addAll(label, pane, button)
        HBox.setHgrow(pane, Priority.ALWAYS)
        box.prefHeight = 60.0
        box.stylesheets.add(ResourceManager.resource(javaClass, "/style/app.css").toExternalForm())
        box.styleClass.add("listbox")
        box.alignment = Pos.CENTER_LEFT

        button.setOnMouseClicked {
            val configBtn = ButtonType(".mcsrc")
            val runnerBtn = ButtonType("runner")
            val cancelBtn = ButtonType("cancel")
            Alert(Alert.AlertType.NONE, "Export As", configBtn, runnerBtn, cancelBtn).apply {
                initStyle(StageStyle.UNDECORATED)
                initOwner(MCSR.gui.stage)

                when (showAndWait().get()) {
                    configBtn -> {
                        // TODO Without Replacing
                        Files.copy(ByteArrayInputStream(ConfigSerializer.serialize(config).second.toByteArray()), Paths.get("${config.name.get()}.mcsrc"), StandardCopyOption.REPLACE_EXISTING)
                    }

                    runnerBtn -> {
                        ConfigurationBuilder.build(config)
                    }
                }
            }
        }
    }

    override fun updateItem(item: Pair<String, McSRConfig>?, empty: Boolean) {
        super.updateItem(item, empty)
        text = null
        if (!empty) {
            label.text = item?.first
            this.config = item?.second!!
        }
        graphic = if (!empty) box else null
    }
}