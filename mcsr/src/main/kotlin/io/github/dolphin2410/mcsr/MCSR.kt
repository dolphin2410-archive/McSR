package io.github.dolphin2410.mcsr

import io.github.dolphin2410.mcsr.api.cli.parser.DefaultParser
import io.github.dolphin2410.mcsr.api.loader.MCSRLoader
import io.github.dolphin2410.mcsr.cli.CLIManager
import io.github.dolphin2410.mcsr.gui.GUIManager
import java.util.concurrent.Executors

object MCSR: io.github.dolphin2410.mcsr.api.AbstractMcSR {

    override lateinit var gui: GUIManager

    override lateinit var cli: CLIManager

    fun start() {
        gui = GUIManager()

        cli = CLIManager()
        cli.addParser(DefaultParser)

        MCSRLoader.mcsr = this
        Executors.newFixedThreadPool(1).submit {
            cli.start()
        }
    }
}