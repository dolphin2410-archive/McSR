package io.github.dolphin2410.mcsr

import io.github.dolphin2410.mcsr.cli.CLIManager
import io.github.dolphin2410.mcsr.cli.parser.DefaultParser
import io.github.dolphin2410.mcsr.gui.GUIManager
import io.github.dolphin2410.mcsr.loader.AbstractMcSR
import io.github.dolphin2410.mcsr.loader.MCSRLoader
import kotlinx.coroutines.*

object MCSR: AbstractMcSR {

    @Suppress("WeakerAccess")

    override lateinit var gui: GUIManager

    @Suppress("WeakerAccess")
    override lateinit var cli: CLIManager

    lateinit var mainScope: CoroutineScope

    @OptIn(DelicateCoroutinesApi::class)
    fun start() {
        gui = GUIManager()
        cli = CLIManager()
        cli.addParser(DefaultParser)
        MCSRLoader.mcsr = this
        GlobalScope.launch(Dispatchers.IO) {
            mainScope = this
            launch {
//                cli.start()
            }
        }
    }
}