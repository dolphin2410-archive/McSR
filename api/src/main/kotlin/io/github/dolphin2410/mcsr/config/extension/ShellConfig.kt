package io.github.dolphin2410.mcsr.config.extension

import io.github.dolphin2410.mcsr.config.AbstractConfiguration
import java.util.*

class ShellConfig: AbstractConfiguration<ShellConfig>() {
    override fun toString(): String {
        return StringJoiner("\n").apply {
            map.forEach {
                add("${it.key}=${it.value}")
            }
        }.toString()
    }
}