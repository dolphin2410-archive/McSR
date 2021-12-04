package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.api.util.ResourceManager
import javafx.scene.Parent

object StyleManager {
    fun style(pane: Parent, path: String) {
        pane.stylesheets.add(ResourceManager.resource(javaClass, path).toExternalForm())
    }
}