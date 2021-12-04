package io.github.dolphin2410.mcsr.config

import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.parser.ConfigSerializer
import io.github.dolphin2410.mcsr.api.util.wrapper.ObservableProperty
import java.io.*

object ConfigurationManager {

    @Suppress("WeakerAccess")
    val configFile = File("configs.mcsrclist")

    private val internalArray = ArrayList<McSRConfig>()

    val map = ObservableProperty(internalArray)

    init {
        configFile.createNewFile()
    }

    fun loadConfig() {
        internalArray.clear()
        DataInputStream(FileInputStream(configFile)).apply {
            while (read() != -1) {
                val size = readInt()
                val inputStream = ByteArrayInputStream(ByteArray(size) {
                    readByte()
                })
                val config = ConfigSerializer.deserialize(inputStream)
                internalArray.add(config)
            }
        }
        map.trigger()
    }

    fun writeConfig(vararg configs: McSRConfig) {
        val stream = DataOutputStream(FileOutputStream(configFile))
        for (config in map.data.apply { addAll(configs) }) {
            stream.apply {
                writeByte(1)
                val serialized = ConfigSerializer.serialize(config).second.toByteArray()
                writeInt(serialized.size)
                serialized.forEach {
                    writeByte(it.toInt())
                }
            }
        }
    }

    fun addConfig(config: McSRConfig) {
        writeConfig(config)
        loadConfig()
    }

    fun removeConfig(config: McSRConfig) {
        if (map.data.remove(config)) {
            writeConfig()
            map.trigger()
        }
    }
}