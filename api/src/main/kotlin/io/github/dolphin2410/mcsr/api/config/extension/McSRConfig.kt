package io.github.dolphin2410.mcsr.api.config.extension

import io.github.dolphin2410.mcsr.api.config.AbstractConfiguration
import io.github.dolphin2410.mcsr.api.config.Variable
import io.github.dolphin2410.mcsr.api.config.config.Config
import io.github.dolphin2410.mcsr.api.config.config.loadConfig
import io.github.dolphin2410.mcsr.api.script.ScriptType
import io.github.dolphin2410.mcsr.api.util.data.PaperData

class McSRConfig private constructor(): AbstractConfiguration<McSRConfig>() {
    companion object {
        @JvmStatic
        fun of(
            jvmArgs: String = "",
            autoReload: Boolean = false,
            autoBackup: Boolean = false,
            serverFolder: String = "./server/",
            serverSoftware: ScriptType = ScriptType.AROXU,
            memory: String = "1G",
            serverUrl: String = PaperData.latestJarUrl,
            filename: String = "default.jar"
        ): McSRConfig {
            return McSRConfig().apply {
                set("jvmArgs", jvmArgs)
                set("autoReload", autoReload)
                set("autoBackup", autoBackup)
                set("serverFolder", serverFolder)
                set("serverSoftware", serverSoftware.name)
                set("memory", memory)
                set("serverUrl", serverUrl)
                set("filename", filename)

                loadConfig()
            }
        }
    }

    @Config
    val filename = Variable<String>()

    // JvmArguments
    @Config
    val jvmArgs = Variable<String>()

    // 자동 리로드
    @Config
    val autoReload = Variable<Boolean>()

    // 자동 백업
    @Config
    val autoBackup = Variable<Boolean>()

    // 서버가 생성되는 위치
    @Config
    val serverFolder = Variable<String>()

    // 서버 스크립트
    @Config
    val serverSoftware = Variable<String>()

    // 할당할 메모리
    @Config
    val memory = Variable<String>()

    // 서버 프로그램 URL
    @Config
    val serverUrl = Variable<String>()

    fun toJson(): JsonConfig {
        return JsonConfig().loadFrom(this)
    }

    fun toConf(): ShellConfig {
        return ShellConfig().loadFrom(this)
    }
}