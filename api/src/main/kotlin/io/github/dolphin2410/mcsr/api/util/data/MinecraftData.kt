package io.github.dolphin2410.mcsr.api.util.data

import java.net.URL

object MinecraftData: ServerData {
    override val versions: List<String>

    init {
        versions = fetchVersions()
    }

    override fun fetchVersions(): List<String> {
        // TODO
        return listOf()
    }

    override fun fetchBuilds(version: String): List<String> {
        // TODO
        return listOf()
    }

    override fun buildUrl(version: String, build: String): URL {
        // TODO
        return URL("")
    }
}