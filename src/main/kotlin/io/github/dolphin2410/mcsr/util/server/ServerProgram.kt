package io.github.dolphin2410.mcsr.util.server

import io.github.dolphin2410.mcsr.util.data.MinecraftData
import io.github.dolphin2410.mcsr.util.data.PaperData
import io.github.dolphin2410.mcsr.util.data.ServerData
import io.github.dolphin2410.mcsr.util.data.SpigotData

enum class ServerProgram(val str: String, val data: ServerData) {
    VANILLA("Vanilla", MinecraftData),
    PAPER("PaperMC", PaperData),
    SPIGOT("Spigot", SpigotData);

    companion object {
        @JvmStatic
        fun from(str: String): ServerProgram {
            return values().find { it.str == str } ?: throw RuntimeException("Invalid Program")
        }
    }
}