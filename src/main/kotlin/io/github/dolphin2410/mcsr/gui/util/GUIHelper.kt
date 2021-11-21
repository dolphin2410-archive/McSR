package io.github.dolphin2410.mcsr.gui.util

import javafx.scene.control.Alert
import javafx.stage.Screen
import javafx.stage.Stage

fun center(stage: Stage) {
    val screen = Screen.getPrimary().visualBounds
    stage.x = (screen.width - stage.width) / 2
    stage.y = (screen.height - stage.height) / 2
}