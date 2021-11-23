package io.github.dolphin2410.mcsr.api.util.web

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object WebManager {
    @Suppress("WeakerAccess")
    var httpClient: HttpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build()

    fun fetchURL(uri: URI, method: RestMethod, body: String = ""): String {
        val request = HttpRequest.newBuilder(uri).method(method.name, HttpRequest.BodyPublishers.ofString(body)).build()
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join().body() ?: ""
    }
}