package io.github.dolphin2410.runner

import io.github.dolphin2410.mcsr.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.config.parser.ConfigManager
import java.io.FileNotFoundException

object DataLoader {
    fun loadConfig(): McSRConfig {
        return ConfigManager.deserialize(
            javaClass.classLoader.getResourceAsStream("config.mcsrc") ?: throw FileNotFoundException("Configuration File mcsrc not found!"))
    }
}