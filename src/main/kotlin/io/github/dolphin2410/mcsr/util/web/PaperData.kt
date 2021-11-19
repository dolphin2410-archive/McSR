package io.github.dolphin2410.mcsr.util.web

import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.*
import java.net.URI

object PaperData {
    suspend fun fetchVersions() = coroutineScope {
        val json = Json.parseToJsonElement(
            WebManager.fetchURL(URI.create("https://papermc.io/api/v2/projects/paper/"), RestMethod.GET)
        ).jsonObject

        (json["versions"] ?: throw RuntimeException("Oh no! paper's api is updated!")).jsonArray.map { it.jsonPrimitive.content }
    }
}