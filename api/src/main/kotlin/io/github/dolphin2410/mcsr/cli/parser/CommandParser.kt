package io.github.dolphin2410.mcsr.cli.parser

import io.github.dolphin2410.mcsr.cli.CommandArguments

interface CommandParser {
    fun parse(commands: CommandArguments): ParseResult
}