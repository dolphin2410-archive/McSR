package io.github.dolphin2410.mcsr.loader

import io.github.dolphin2410.mcsr.cli.parser.CommandParser

interface AbstractCLIManager {
    val parsers: List<CommandParser>
    suspend fun start()
    fun addParser(parser: CommandParser)
    fun removeParser(parser: CommandParser)
}