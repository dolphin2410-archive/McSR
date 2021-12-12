package io.github.dolphin2410.mcsr.api.script

import io.github.dolphin2410.mcsr.api.util.GitApi
import java.net.URL

data class ScriptLink(val baseUrl: String, val scriptType: ScriptType) {
    fun getUrl(architecture: Architecture): URL {
        return URL(
            when (scriptType) {
                ScriptType.MONUN -> baseUrl
                ScriptType.AROXU -> {
                    val latest = GitApi.getLatestRelease("aroxu", "server-script").join()
                    baseUrl + when (architecture) {
                        Architecture.WINDOWS_64,
                        Architecture.WINDOWS_32 -> "releases/download/$latest/windows.zip"
                        Architecture.LINUX_64,
                        Architecture.LINUX_32 -> "releases/download/$latest/linux.zip"
                        Architecture.DARWIN_64 -> "releases/download/$latest/darwin.zip"
                        else -> throw RuntimeException("Unsupported")
                    }
                }
                ScriptType.DOLPHIN2410 -> {
                    val latest = GitApi.getLatestRelease("aroxu", "server-script").join()
                    baseUrl + when (architecture) {
                        Architecture.WINDOWS_64 -> "releases/download/$latest/server_windows_x64.exe.zip"
                        Architecture.WINDOWS_32 -> "releases/download/$latest/server_windows_x32.exe.zip"
                        Architecture.LINUX_64 -> "releases/download/$latest/server_linux_x64.zip"
                        Architecture.LINUX_32 -> "releases/download/$latest/server_linux_x32.zip"
                        Architecture.DARWIN_64 -> "releases/download/$latest/server_darwin_x64.zip"
                        else -> throw RuntimeException("Unsupported")
                    }
                }
            }
        )
    }
}