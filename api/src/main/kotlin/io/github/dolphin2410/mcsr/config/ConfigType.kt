package io.github.dolphin2410.mcsr.config

enum class ConfigType(val code: Byte, val type: Class<*>) {
    BOOLEAN(0, Boolean::class.java),
    INTEGER(1, Int::class.java),
    STRING(2, String::class.java);

    companion object {
        @JvmStatic
        fun fromSafe(code: Byte): ConfigType? {
            return values().find { it.code == code }
        }

        @JvmStatic
        fun from(code: Byte): ConfigType {
            return fromSafe(code) ?: throw RuntimeException("Invalid Code Number")
        }

        @JvmStatic
        fun fromTypeSafe(type: Class<*>): ConfigType? {
            return values().find { it.type == type }
        }

        @JvmStatic
        fun fromType(type: Class<*>): ConfigType {
            return fromTypeSafe(type) ?: throw RuntimeException("Invalid Type")
        }
    }
}