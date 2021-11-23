package io.github.dolphin2410.mcsr.api.cli.parser

import io.github.dolphin2410.mcsr.api.cli.CommandArguments

interface CommandParser {
    fun parse(commands: CommandArguments): ParseResult
}