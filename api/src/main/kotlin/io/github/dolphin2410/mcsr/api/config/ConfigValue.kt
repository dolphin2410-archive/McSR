package io.github.dolphin2410.mcsr.api.config

import io.github.dolphin2410.mcsr.api.util.wrapper.StringArray

class ConfigValue private constructor(val value: Any, val type: ConfigType) {
    constructor(value: Int): this(value as Any, ConfigType.INTEGER)
    constructor(value: Boolean): this(value as Any, ConfigType.BOOLEAN)
    constructor(value: String): this(value as Any, ConfigType.STRING)
    constructor(value: StringArray): this(value as Any, ConfigType.STRING_ARRAY)

    companion object {
        @JvmStatic
        fun force(value: Any): ConfigValue {
            @Suppress("unchecked_cast")
            return when (value) {
                is Int -> ConfigValue(value)
                is Boolean -> ConfigValue(value)
                is String -> ConfigValue(value)
                is StringArray -> ConfigValue(value)
                is Array<*> -> {
                    if (value::class.java.componentType == String::class.java) {
                        ConfigValue(StringArray(value as Array<String>))
                    } else {
                        throw RuntimeException("Invalid ArrayType")
                    }
                }
                else -> throw RuntimeException("Invalid")
            }
        }
    }

    fun hash(): ByteArray {
        return value.toString().toByteArray()
    }
}