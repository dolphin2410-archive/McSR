package io.github.dolphin2410.mcsr.config

import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.config.parser.ConfigSerializer
import io.github.dolphin2410.mcsr.api.util.wrapper.ObservableProperty
import java.io.*

object ConfigurationManager {

    @Suppress("WeakerAccess")
    val configFile = File("configs.mcsrclist")

    private val internalMap = ArrayList<Pair<String, McSRConfig>>()

    val map = ObservableProperty(internalMap)

    init {
        configFile.createNewFile()
    }

    fun loadConfig() {
        internalMap.clear()
        DataInputStream(FileInputStream(configFile)).apply {
            while (read() != -1) {
                // FIXME BUG!!!!
                val name = readUTF()
                val size = readInt()
                val inputStream = ByteArrayInputStream(ByteArray(size) {
                    readByte()
                })
                val config = ConfigSerializer.deserialize(inputStream)
                internalMap.add(name to config)
            }
        }
        map.trigger()
    }

    fun addConfig(name: String, config: McSRConfig) {
        DataOutputStream(FileOutputStream(configFile, true)).apply {
            writeByte(1)
            writeUTF(name)
            val serialized = ConfigSerializer.serialize(config).second.toByteArray()
            writeInt(serialized.size)
            serialized.forEach {
                writeByte(it.toInt())
            }
        }
        println("Configuration $name saved!")
        loadConfig()
    }
}