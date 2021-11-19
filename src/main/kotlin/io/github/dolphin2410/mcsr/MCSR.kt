package io.github.dolphin2410.mcsr

import io.github.dolphin2410.mcsr.cli.CLIManager
import io.github.dolphin2410.mcsr.cli.parser.DefaultParser
import io.github.dolphin2410.mcsr.gui.GUIManager
import kotlinx.coroutines.*

object MCSR {
    lateinit var gui: GUIManager
    lateinit var cli: CLIManager
    lateinit var mainScope: CoroutineScope

    @JvmStatic
    fun main(args: Array<String>) {
        gui = GUIManager()
        cli = CLIManager()
        cli.addParser(DefaultParser)

        runBlocking(Dispatchers.IO) {
            mainScope = this

            launch {
                cli.start()
            }

            launch {
                gui.start()
            }
        }
    }
}