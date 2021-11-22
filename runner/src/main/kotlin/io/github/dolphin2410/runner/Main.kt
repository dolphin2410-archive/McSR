package io.github.dolphin2410.runner

import io.github.dolphin2410.mcsr.config.config.asEnum
import io.github.dolphin2410.mcsr.config.config.loadConfig
import io.github.dolphin2410.mcsr.script.Architecture
import io.github.dolphin2410.mcsr.script.ScriptType

fun main(args: Array<String>) {
    val config = DataLoader.loadConfig()
    config.loadConfig()
    val scriptType = config.serverSoftware.get().asEnum<ScriptType>() ?: throw RuntimeException("Invalid ServerScriptType: ${config.serverSoftware}")
    scriptType.link.getUrl(Architecture.get())
}