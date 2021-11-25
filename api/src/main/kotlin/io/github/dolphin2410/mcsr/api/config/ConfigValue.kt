package io.github.dolphin2410.mcsr.api.config

class ConfigValue private constructor(val value: Any, val type: ConfigType) {
    constructor(value: Int): this(value as Any, ConfigType.INTEGER)
    constructor(value: Boolean): this(value as Any, ConfigType.BOOLEAN)
    constructor(value: String): this(value as Any, ConfigType.STRING)

    companion object {
        @JvmStatic
        fun force(value: Any): ConfigValue {
            return when (value) {
                is Int -> ConfigValue(value)
                is Boolean -> ConfigValue(value)
                is String -> ConfigValue(value)
                else -> throw RuntimeException("Invalid")
            }
        }
    }
}