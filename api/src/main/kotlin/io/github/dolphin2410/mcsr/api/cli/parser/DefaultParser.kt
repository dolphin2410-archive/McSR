package io.github.dolphin2410.mcsr.api.cli.parser

import io.github.dolphin2410.mcsr.api.cli.CommandArguments
import io.github.dolphin2410.mcsr.api.loader.MCSRLoader
import kotlin.system.exitProcess

object DefaultParser: CommandParser {
    override fun parse(commands: CommandArguments): ParseResult {
        return commands.parse {
            command {
                val type = argumentFlag("server-type={type}") { data ->
                    data["type"]
                }

                val version = argumentFlag("version={version}") { data ->
                    data["version"]
                }

                val serverDirectory = argumentFlag("server-directory={serverDirectory}") { data ->
                    data["serverDirectory"]
                }

                val memory = argumentFlag("memory={memory}") { data ->
                    data["memory"]
                }
            }
        }
    }
}