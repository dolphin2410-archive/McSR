package io.github.dolphin2410.mcsr.api.config.extension

import io.github.dolphin2410.mcsr.api.config.AbstractConfiguration
import io.github.dolphin2410.mcsr.api.config.config.Config
import io.github.dolphin2410.mcsr.api.config.config.ScriptConfig
import io.github.dolphin2410.mcsr.api.config.config.loadConfig
import io.github.dolphin2410.mcsr.api.script.ScriptType
import io.github.dolphin2410.mcsr.api.util.data.PaperData
import io.github.dolphin2410.mcsr.api.util.wrapper.Property
import io.github.dolphin2410.mcsr.api.util.wrapper.StringArray
import java.security.MessageDigest

class McSRConfig private constructor(): AbstractConfiguration<McSRConfig>() {
    companion object {
        @JvmStatic
        fun of(
            jvmArgs: String = "",
            autoReload: Boolean = false,
            autoBackup: Boolean = false,
            serverFolder: String = "./server/",
            serverSoftware: ScriptType = ScriptType.AROXU,
            memory: Int = 1,
            serverUrl: String = PaperData.latestJarUrl,
            filename: String = "default.jar",
            name: String = "sampleconf",
            plugins: StringArray = StringArray(arrayOf()),
            hash: String = MessageDigest.getInstance("SHA3-256").digest("".toByteArray()).joinToString()
        ): McSRConfig {
            return McSRConfig().apply {
                set("jvmArgs", StringArray(jvmArgs.split(" ").toTypedArray()))
                set("autoReload", autoReload)
                set("autoBackup", autoBackup)
                set("serverFolder", serverFolder)
                set("serverSoftware", serverSoftware.name)
                set("memory", memory)
                set("serverUrl", serverUrl)
                set("filename", filename)
                set("name", name)
                set("plugins", plugins)
                set("hash", hash)
                loadConfig()
            }
        }
    }

    @Config
    val filename = Property<String>()

    // JvmArguments
    @Config
    @ScriptConfig
    val jvmArgs = Property<StringArray>()

    @Config
    val hash = Property<String>()

    // 자동 리로드
    @Config
    @ScriptConfig
    val autoReload = Property<Boolean>()

    // 자동 백업
    @Config
    @ScriptConfig
    val autoBackup = Property<Boolean>()

    // 서버가 생성되는 위치
    @Config
    val serverFolder = Property<String>()

    // 서버 스크립트
    @Config
    val serverSoftware = Property<String>()

    // 할당할 메모리
    @Config
    @ScriptConfig
    val memory = Property<Int>()

    // 서버 프로그램 URL
    @Config
    @ScriptConfig
    val serverUrl = Property<String>()

    // Plugin List
    @Config
    @ScriptConfig
    val plugins = Property<StringArray>()

    @Config
    val name = Property<String>()

    fun toJson(): JsonConfig {
        return JsonConfig().loadFrom(this)
    }

    fun toConf(): ShellConfig {
        return ShellConfig().loadFrom(this)
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is McSRConfig) return false
        return other.hash.get() == hash.get()
    }

    override fun hashCode(): Int {
        var result = filename.hashCode()
        result = 31 * result + jvmArgs.hashCode()
        result = 31 * result + hash.hashCode()
        result = 31 * result + autoReload.hashCode()
        result = 31 * result + autoBackup.hashCode()
        result = 31 * result + serverFolder.hashCode()
        result = 31 * result + serverSoftware.hashCode()
        result = 31 * result + memory.hashCode()
        result = 31 * result + serverUrl.hashCode()
        result = 31 * result + plugins.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}