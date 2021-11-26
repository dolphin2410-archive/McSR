package io.github.dolphin2410.mcsr.api.config.parser

import io.github.dolphin2410.mcsr.api.config.ConfigType
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.util.wrapper.StringArray
import java.io.*

object ConfigSerializer {
    fun deserialize(inputStream: InputStream): McSRConfig {
        return McSRConfig.of().apply {
            val dataInputStream = DataInputStream(inputStream)
            while (dataInputStream.read() != -1) {
                val type = ConfigType.from(dataInputStream.readByte())
                val key = dataInputStream.readUTF()
                setUnsafe(key, when(type) {
                    ConfigType.STRING -> dataInputStream.readUTF()
                    ConfigType.BOOLEAN -> dataInputStream.readByte() == 1.toByte()
                    ConfigType.INTEGER -> dataInputStream.readInt()
                    ConfigType.STRING_ARRAY -> {
                        StringArray(Array(dataInputStream.readInt()) {
                            dataInputStream.readUTF()
                        })
                    }
                })
            }
        }
    }

    fun serialize(config: McSRConfig): Pair<DataOutputStream, ByteArrayOutputStream> {
        val bos = ByteArrayOutputStream()
        return DataOutputStream(bos).apply {
            config.data.forEach {
                // This will determine whether the stream reached the end
                writeByte(0)
                writeByte(it.value.type.code.toInt())
                writeUTF(it.key)
                when (it.value.type) {
                    ConfigType.INTEGER -> writeInt(it.value.value as Int)
                    ConfigType.BOOLEAN -> writeByte(if(it.value.value as Boolean) 1 else 0)
                    ConfigType.STRING -> writeUTF(it.value.value as String)
                    ConfigType.STRING_ARRAY -> {
                        writeInt((it.value.value as StringArray).size)
                        (it.value.value as StringArray).array.forEach { item ->
                            writeUTF(item)
                        }
                    }
                }
            }
        } to bos
    }
}