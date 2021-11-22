package io.github.dolphin2410.mcsr.config.parser

import io.github.dolphin2410.mcsr.config.ConfigType
import io.github.dolphin2410.mcsr.config.extension.McSRConfig
import java.io.*

object ConfigManager {
    fun deserialize(inputStream: InputStream): McSRConfig {
        return McSRConfig().apply {
            val dataInputStream = DataInputStream(inputStream)
            while (dataInputStream.readByte() == 0.toByte()) {
                val type = ConfigType.from(dataInputStream.readByte())
                val key = dataInputStream.readUTF()
                setUnsafe(key, when(type) {
                    ConfigType.STRING -> dataInputStream.readUTF()
                    ConfigType.BOOLEAN -> dataInputStream.readByte() == 1.toByte()
                    ConfigType.INTEGER -> dataInputStream.readInt()
                })
            }
        }
    }

    fun serialize(config: McSRConfig): OutputStream {
        return DataOutputStream(ByteArrayOutputStream()).apply {
            config.data.forEach {
                writeByte(1)
                writeUTF(it.key)
                when (it.value.type) {
                    ConfigType.INTEGER -> writeInt(it.value.value as Int)
                    ConfigType.BOOLEAN -> writeByte(if(it.value.value as Boolean) 1 else 0)
                    ConfigType.STRING -> writeUTF(it.value.value as String)
                }
            }
            writeByte(0)
        }
    }
}