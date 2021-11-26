package io.github.dolphin2410.mcsr.api.config.extension

import io.github.dolphin2410.mcsr.api.config.AbstractConfiguration
import io.github.dolphin2410.mcsr.api.util.wrapper.StringArray
import java.util.*

class ShellConfig: AbstractConfiguration<ShellConfig>() {
    override fun toString(): String {
        return StringJoiner("\n").apply {
            map.forEach {
                if (it.value.value is StringArray) {
                    add("""${it.key}=(
                        ${StringJoiner("\n").apply { (it.value.value as StringArray).toList().forEach { item -> add(item) } }}
                    )""".trimIndent())
                } else {
                    add("${it.key}=${it.value}")
                }
            }
        }.toString()
    }
}