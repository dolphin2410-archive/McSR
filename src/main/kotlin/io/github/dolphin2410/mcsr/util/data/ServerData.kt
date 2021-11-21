package io.github.dolphin2410.mcsr.util.data

interface ServerData {
    val versions: List<String>

    fun fetchVersions(): List<String>

    fun fetchBuilds(version: String): List<String>
}