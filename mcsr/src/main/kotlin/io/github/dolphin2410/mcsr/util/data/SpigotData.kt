package io.github.dolphin2410.mcsr.util.data

object SpigotData: ServerData {
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
}