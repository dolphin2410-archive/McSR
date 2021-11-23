package io.github.dolphin2410.mcsr.api.util.web

import java.io.File
import java.net.URI
import java.nio.file.Files

object Downloader {
    fun downloadTo(uri: URI, target: File) {
        Files.copy(uri.toURL().openStream(), target.toPath())
    }
}