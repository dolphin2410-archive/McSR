package io.github.dolphin2410.mcsr.api.script

import io.github.dolphin2410.mcsr.api.util.GitApi
import java.net.URL

data class ScriptLink(val baseUrl: String, val scriptType: ScriptType) {
    fun getUrl(architecture: Architecture): URL {
        return URL(
            when (scriptType) {
                ScriptType.MONUN -> baseUrl
                ScriptType.AROXU -> {
                    val latest = GitApi.getLatestRelease("aroxu", "server-script")
                    concentrateUrl(
                        baseUrl,
                        when (architecture) {
                            Architecture.WINDOWS_64 -> "/releases/download/$latest/server_windows_x64.zip"
                            Architecture.WINDOWS_32 -> "/releases/download/$latest/server_windows_x32.zip"
                            Architecture.LINUX_64 -> "/releases/download/$latest/server_linux_x64.zip"
                            Architecture.LINUX_32 -> "/releases/download/$latest/server_linux_x32.zip"
                            Architecture.DARWIN_64 -> "/releases/download/$latest/server_darwin_x64.zip"
                            else -> throw RuntimeException("Unsupported")
                        }
                    )
                }
            }
        )
    }

    private fun concentrateUrl(first: String, second: String): String {
        return StringBuilder(first).apply {
            append(first)
            if (!first.endsWith("/") && !second.startsWith("/")) {
                append("/")
            }
            append(second)
        }.toString()
    }
}