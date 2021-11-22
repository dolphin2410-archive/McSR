package io.github.dolphin2410.mcsr.config

class ConfigValue private constructor(val value: Any, val type: ConfigType) {
    constructor(value: Int): this(value as Any, ConfigType.INTEGER)
    constructor(value: Boolean): this(value as Any, ConfigType.BOOLEAN)
    constructor(value: String): this(value as Any, ConfigType.STRING)
}