package io.github.dolphin2410.mcsr.api.config.extension

import io.github.dolphin2410.mcsr.api.config.AbstractConfiguration
import io.github.dolphin2410.mcsr.api.config.mappers.Mapper
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class JsonConfig: AbstractConfiguration<JsonConfig>() {
    fun toJson(mapper: Mapper? = null): JsonObject {
        return JsonObject(scriptConfigMap.mapKeys {
            mapper?.map(it.key) ?: it.key
        }.mapValues {
            when (it.value.value) {
                is Int -> JsonPrimitive(it.value.value as Int)
                is String -> JsonPrimitive(it.value.value as String)
                is Boolean -> JsonPrimitive(it.value.value as Boolean)
                else -> throw RuntimeException("Invalid Configuration Primitive Value")
            }
        })
    }
}