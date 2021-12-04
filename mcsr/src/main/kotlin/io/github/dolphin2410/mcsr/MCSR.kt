package io.github.dolphin2410.mcsr

import io.github.dolphin2410.mcsr.api.AbstractMcSR
import io.github.dolphin2410.mcsr.api.cli.parser.DefaultParser
import io.github.dolphin2410.mcsr.api.loader.MCSRLoader
import io.github.dolphin2410.mcsr.cli.CLIManager
import io.github.dolphin2410.mcsr.gui.GUIManager

object MCSR: AbstractMcSR {

    override lateinit var gui: GUIManager

    override lateinit var cli: CLIManager

    override fun start(args: Array<String>) {
        gui = GUIManager()

        cli = CLIManager()

        MCSRLoader.mcsr = this

        cli.addParser(DefaultParser)
        cli.start(args)
    }
}