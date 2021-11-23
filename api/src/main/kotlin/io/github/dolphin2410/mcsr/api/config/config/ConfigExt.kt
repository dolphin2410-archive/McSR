package io.github.dolphin2410.mcsr.api.config.config

import io.github.dolphin2410.mcsr.api.config.AbstractConfiguration
import io.github.dolphin2410.mcsr.api.config.Variable

fun AbstractConfiguration<*>.loadConfig() {
    this::class.java.declaredFields.forEach {
        if (it.isAnnotationPresent(Config::class.java)) {
            it.isAccessible = true
            val variable = it.get(this) as Variable<*>
            variable.forceSet(this.data[it.name]?.value ?: throw RuntimeException("Missing Configuration Field"))
        }
    }
}

inline fun <reified T: Enum<T>> String.asEnum(): T? {
    return T::class.java.enumConstants.find { it.name == this }
}