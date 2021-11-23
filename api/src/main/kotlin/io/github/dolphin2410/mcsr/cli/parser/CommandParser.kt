package io.github.dolphin2410.mcsr.cli.parser

interface CommandParser {
    fun parse(commands: io.github.dolphin2410.mcsr.cli.CommandArguments): ParseResult
}