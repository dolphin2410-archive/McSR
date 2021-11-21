package io.github.dolphin2410.mcsr

import io.github.dolphin2410.mcsr.cli.CLIManager
import io.github.dolphin2410.mcsr.cli.parser.DefaultParser
import io.github.dolphin2410.mcsr.gui.GUIManager
import io.github.dolphin2410.mcsr.util.data.MinecraftData
import io.github.dolphin2410.mcsr.util.data.PaperData
import kotlinx.coroutines.*

object MCSR {

    @Suppress("WeakerAccess")
    lateinit var gui: GUIManager

    @Suppress("WeakerAccess")
    lateinit var cli: CLIManager

    lateinit var mainScope: CoroutineScope

    @OptIn(DelicateCoroutinesApi::class)
    fun start() {
        gui = GUIManager()
        cli = CLIManager()
        cli.addParser(DefaultParser)
        GlobalScope.launch(Dispatchers.IO) {
            mainScope = this
            launch {
//                cli.start()
            }
        }
    }
}