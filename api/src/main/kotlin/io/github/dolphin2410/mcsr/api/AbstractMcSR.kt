package io.github.dolphin2410.mcsr.api

import io.github.dolphin2410.mcsr.api.cli.AbstractCLIManager
import io.github.dolphin2410.mcsr.api.gui.AbstractGUIManager

interface AbstractMcSR {
    val gui: AbstractGUIManager
    val cli: AbstractCLIManager
    fun start(args: Array<String>)
}