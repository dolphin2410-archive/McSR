package io.github.dolphin2410.mcsr.config

import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig

object ConfigurationManager {
    fun loadConfig() {

    }

    fun addConfig(name: String, mcSRConfig: McSRConfig) {
        println("Configuration $name saved!")
    }
}