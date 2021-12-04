package io.github.dolphin2410.mcsr.gui.components

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.parser.ConfigSerializer
import io.github.dolphin2410.mcsr.api.util.ConfigurationBuilder
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import io.github.dolphin2410.mcsr.config.ConfigurationManager
import io.github.dolphin2410.mcsr.gui.AlertManager
import io.github.dolphin2410.mcsr.gui.StyleManager
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

class ConfigurationBar: ListCell<McSRConfig>() {
    private val box = HBox()
    private val label = Label()
    private val button = Button("x")
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
            when (AlertManager.alert("Delete?", ButtonType.OK, ButtonType.CANCEL) { StyleManager.style(dialogPane, "/style/alert.css") }) {
                ButtonType.OK -> ConfigurationManager.removeConfig(config)
            }
        }

        box.setOnMouseClicked {
            val configBtn = ButtonType(".mcsrc")
            val runnerBtn = ButtonType("runner")
            val cancelBtn = ButtonType("cancel")
            Alert(Alert.AlertType.NONE, "Export As", configBtn, runnerBtn, cancelBtn).apply {
                initOwner(MCSR.gui.stage)
                initStyle(StageStyle.UNDECORATED)
                StyleManager.style(this.dialogPane, "/style/alert.css")

                when (showAndWait().get()) {
                    configBtn -> {
                        Files.copy(ByteArrayInputStream(ConfigSerializer.serialize(config).second.toByteArray()), Paths.get("${config.name.get()}.mcsrc"), StandardCopyOption.REPLACE_EXISTING)
                    }

                    runnerBtn -> {
                        ConfigurationBuilder.build(config)
                    }
                }
            }
        }
    }

    override fun updateItem(item: McSRConfig?, empty: Boolean) {
        super.updateItem(item, empty)
        text = null

        item?.let {
            val name: String by it.name

            if (!empty) {
                label.text = name
                config = it
            }
        }

        graphic = if (!empty) box else null
    }
}