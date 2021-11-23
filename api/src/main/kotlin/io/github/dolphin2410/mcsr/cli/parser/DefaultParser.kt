package io.github.dolphin2410.mcsr.cli.parser

import io.github.dolphin2410.mcsr.loader.MCSRLoader
import kotlin.system.exitProcess

object DefaultParser: CommandParser {
    override fun parse(commands: io.github.dolphin2410.mcsr.cli.CommandArguments): ParseResult {
        return commands.parse {
            command("launch") {

                flag("nogui") {
                    println("WA")
                }

                noflag("nogui") {
                    println("GUI!")
                }

                MCSRLoader.mcsr.gui.close()
            }

            command("export-config") {
                println("Exporting config")
            }

            command("exit") {
                println("GOODBYE!")
                MCSRLoader.mcsr.gui.close()
                exitProcess(0)
            }
        }
    }
}