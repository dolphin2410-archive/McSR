package io.github.dolphin2410.mcsr.api.cli

import io.github.dolphin2410.mcsr.api.cli.parser.CommandParser

interface AbstractCLIManager {
    val parsers: List<CommandParser>
    fun start(cmd: Array<String>)
    fun addParser(parser: CommandParser)
    fun removeParser(parser: CommandParser)
}