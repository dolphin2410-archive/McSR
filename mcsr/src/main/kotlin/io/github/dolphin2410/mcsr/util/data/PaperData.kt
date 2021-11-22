package io.github.dolphin2410.mcsr.util.data

import io.github.dolphin2410.mcsr.util.RestMethod
import io.github.dolphin2410.mcsr.util.WebManager
import kotlinx.serialization.json.*
import java.net.URI

object PaperData: ServerData {
    override val versions: List<String>

    init {
        versions = fetchVersions()
    }

    override fun fetchVersions(): List<String> {
        val json = Json.parseToJsonElement(
            WebManager.fetchURL(URI.create("https://papermc.io/api/v2/projects/paper/"), RestMethod.GET)
        ).jsonObject

        return (json["versions"] ?: throw RuntimeException("Oh no! paper's api is updated!")).jsonArray.map { it.jsonPrimitive.content }.reversed()
    }

    override fun fetchBuilds(version: String): List<String> {
        val json = Json.parseToJsonElement(
            WebManager.fetchURL(
                URI.create("https://papermc.io/api/v2/projects/paper/versions/$version"),
                RestMethod.GET
            )
        ).jsonObject

        return (json["builds"] ?: throw RuntimeException("Oh no! paper's api is updated!")).jsonArray.map { it.jsonPrimitive.content }.reversed()
    }
}