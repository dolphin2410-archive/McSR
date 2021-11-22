package io.github.dolphin2410.mcsr.util

import java.io.File
import java.net.URI
import java.nio.file.Files

object Downloader {
    fun downloadTo(uri: URI, target: File) {
        Files.copy(uri.toURL().openStream(), target.toPath())
    }
}