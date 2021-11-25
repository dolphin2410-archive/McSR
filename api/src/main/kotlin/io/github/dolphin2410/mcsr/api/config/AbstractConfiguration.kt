package io.github.dolphin2410.mcsr.api.config

import kotlin.collections.HashMap

open class AbstractConfiguration<T: AbstractConfiguration<T>> {
    val map = HashMap<String, ConfigValue>()

    internal val data: Map<String, ConfigValue>
        get() = map.toMap()

    operator fun set(key: String, value: Int) {
        map[key] = ConfigValue(value)
    }

    operator fun set(key: String, value: Boolean) {
        map[key] = ConfigValue(value)
    }

    operator fun set(key: String, value: String) {
        map[key] = ConfigValue(value)
    }

    internal fun setUnsafe(key: String, value: Any) {
        map[key] = when(value) {
            is Int -> ConfigValue(value)
            is String -> ConfigValue(value)
            is Boolean -> ConfigValue(value)
            else -> throw RuntimeException("")
        }
    }

    fun loadFrom(other: AbstractConfiguration<*>): T {
        map.clear()
        other.map.forEach {
            map[it.key] = it.value
        }
        @Suppress("unchecked_cast")
        return this as T
    }

    fun loadFrom(map: Map<String, ConfigValue>) {
        this.map.clear()
        map.forEach {
            this.map[it.key] = it.value
        }
    }

    operator fun get(key: String): Any? {
        return map[key]?.value
    }

    fun getString(key: String): String {
        return map[key]?.value as? String ?: throw RuntimeException("No such string value: $key")
    }

    fun getInt(key: String): Int {
        return map[key]?.value as? Int ?: throw RuntimeException("No such integer value: $key")
    }

    fun getBoolean(key: String): Boolean {
        return map[key]?.value as? Boolean ?: throw RuntimeException("No such boolean value: $key")
    }
}