package io.github.dolphin2410.mcsr.gui

import java.awt.GridLayout
import javax.swing.JEditorPane
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane


class GUIManager : JFrame("Title") {

    fun start() {
        defaultCloseOperation = EXIT_ON_CLOSE

        contentPane = JPanel()
        contentPane.layout = GridLayout(1, 2, 2, 2)

        val editorPane = JEditorPane("http://www.java2s.com")
        editorPane.contentType = "text/html"
        editorPane.isEditable = false
//        editorPane.text = javaClass.classLoader.getResourceAsStream("my.html")?.let {
//            BufferedReader(InputStreamReader(it)).readText()
//        } ?: "NO_FILE"

        val scrollPane = JScrollPane()
        scrollPane.setViewportView(editorPane)

        contentPane.add(scrollPane)

        setSize(960, 540)
        isLocationByPlatform = true
        isVisible = true
        setLocationRelativeTo(null)
    }

    fun close() {

    }
}