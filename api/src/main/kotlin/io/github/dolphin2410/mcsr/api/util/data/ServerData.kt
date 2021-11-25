package io.github.dolphin2410.mcsr.api.util.data

import java.net.URL

interface ServerData {
    val versions: List<String>

    fun fetchVersions(): List<String>

    fun fetchBuilds(version: String): List<String>

    fun buildUrl(version: String, build: String): URL
}