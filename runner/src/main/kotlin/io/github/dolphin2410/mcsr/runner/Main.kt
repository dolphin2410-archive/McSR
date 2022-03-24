package io.github.dolphin2410.mcsr.runner

import io.github.dolphin2410.mcsr.api.cli.CommandArguments
import io.github.dolphin2410.mcsr.api.config.config.asEnum
import io.github.dolphin2410.mcsr.api.config.config.loadConfig
import io.github.dolphin2410.mcsr.api.config.config.saveConfig
import io.github.dolphin2410.mcsr.api.script.Architecture
import io.github.dolphin2410.mcsr.api.script.ScriptType

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            Runtime.getRuntime().addShutdownHook(Thread {
                DataLoader.processes.forEach {
                    it.destroyForcibly()
                }
            })

            var architecture = Architecture.get()

            CommandArguments(args.toList()).parse {
                command {
                    argumentFlag("architecture={architecture}") {
                        it["architecture"]?.let { arch ->
                            architecture = Architecture.get(arch)
                        }
                    }
                }
            }

            val config = DataLoader.loadConfig()
            config.loadConfig()
            config.saveConfig()

            val scriptType = config.serverSoftware.get().asEnum<ScriptType>() ?: throw RuntimeException("Invalid ScriptType: ${config.serverSoftware.get()}")
            DataLoader.execute(scriptType.link.getUrl(architecture), config)
        }
    }
}