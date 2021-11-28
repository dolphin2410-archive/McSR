package io.github.dolphin2410.mcsr.api.util.data

import io.github.dolphin2410.mcsr.api.util.web.RestMethod
import io.github.dolphin2410.mcsr.api.util.web.WebManager
import kotlinx.serialization.json.*
import java.net.URI
import java.net.URL

object PaperData: ServerData {
    override val versions: List<String>

    val latestJarUrl: String

    @Suppress("WeakerAccess")
    val latestBuild: String

    @Suppress("WeakerAccess")
    val latestVersion: String

    init {
        versions = fetchVersions()
        latestVersion = versions.first()
        latestBuild = fetchBuilds(latestVersion).first()
        latestJarUrl = "https://papermc.io/api/v2/projects/paper/versions/$latestVersion/builds/$latestBuild/downloads/paper-$latestVersion-$latestBuild.jar"
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

    override fun buildUrl(version: String, build: String): URL {
        return URL("https://papermc.io/api/v2/projects/paper/versions/$version/builds/$build/downloads/paper-$version-$build.jar")
    }
}