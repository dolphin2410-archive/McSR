package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.util.coroutine.awaitCoroutine
import io.github.dolphin2410.mcsr.util.web.PaperData
import java.awt.Dimension
import java.awt.event.WindowEvent
import javax.swing.*

class GUIManager: JFrame("Minecraft Server Runner") {
    fun start() {
        size = Dimension(1920 / 2, 1080 / 2)
        setLocationRelativeTo(null)
        isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = false
        init()
    }

    fun close() {
        dispatchEvent(WindowEvent(this, WindowEvent.WINDOW_CLOSING))
    }

    private fun init() {
        val versions = awaitCoroutine(PaperData::fetchVersions)
        // GUI HANDLING
    }
}