package io.github.dolphin2410.mcsr.util

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture

object GitApi {
    fun getLatestRelease(user: String, repo: String): CompletableFuture<String> {
        val future = CompletableFuture<String>()
        val client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build()
        val request = HttpRequest.newBuilder(URI.create("https://api.github.com/repos/$user/$repo/releases/latest")).GET().build()

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply {
                val latest = Json.parseToJsonElement(it.body()).jsonObject["tag_name"] ?: throw RuntimeException("Error while fetching latest release version")
                future.complete(latest.jsonPrimitive.content)
            }

        return future
    }
}