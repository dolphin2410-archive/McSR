package io.github.dolphin2410.mcsr.cli

import io.github.dolphin2410.mcsr.api.cli.AbstractCLIManager
import io.github.dolphin2410.mcsr.api.cli.CommandArguments
import io.github.dolphin2410.mcsr.api.cli.parser.CommandParser
import io.github.dolphin2410.mcsr.api.cli.parser.ParseResult
import java.io.BufferedReader
import java.io.InputStreamReader

class CLIManager: AbstractCLIManager {
    private val _parsers = ArrayList<CommandParser>()

    override val parsers: List<CommandParser>
        get() = _parsers.toList()

    override fun start(cmd: Array<String>) {
        run {
            _parsers.forEach {
                if (it.parse(
                    CommandArguments(
                        cmd.toList()
                    )
                ) == ParseResult.SUCCESS) return@run
            }
            println("No such command found")
        }
    }

    override fun addParser(parser: CommandParser) {
        _parsers.add(parser)
    }

    override fun removeParser(parser: CommandParser) {
        _parsers.remove(parser)
    }
}