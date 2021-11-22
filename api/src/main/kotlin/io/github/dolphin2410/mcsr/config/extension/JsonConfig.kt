package io.github.dolphin2410.mcsr.config.extension

import io.github.dolphin2410.mcsr.config.AbstractConfiguration
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class JsonConfig: AbstractConfiguration<JsonConfig>() {
    fun toJson(): JsonObject {
        return JsonObject(map.mapValues {
            when (it.value.value) {
                is Int -> JsonPrimitive(it.value.value as Int)
                is String -> JsonPrimitive(it.value.value as String)
                is Boolean -> JsonPrimitive(it.value.value as Boolean)
                else -> throw RuntimeException("Invalid Configuration Primitive Value")
            }
        })
    }
}