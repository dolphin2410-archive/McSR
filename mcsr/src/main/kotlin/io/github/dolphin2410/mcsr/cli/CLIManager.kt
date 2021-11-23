package io.github.dolphin2410.mcsr.cli

import io.github.dolphin2410.mcsr.cli.parser.CommandParser
import io.github.dolphin2410.mcsr.cli.parser.ParseResult
import io.github.dolphin2410.mcsr.loader.AbstractCLIManager
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader

class CLIManager: AbstractCLIManager {
    private val _parsers = ArrayList<CommandParser>()

    override val parsers: List<CommandParser>
        get() = _parsers.toList()

    override suspend fun start() = coroutineScope {
        while (true) {
            run {
                    print("> ")
                    val reader = BufferedReader(InputStreamReader(System.`in`))
                    val cmd = withContext(Dispatchers.IO) { safe(reader::readLine) }
                    _parsers.forEach {
                        if (it.parse(
                                io.github.dolphin2410.mcsr.cli.CommandArguments(
                                    cmd.replace("\\s+".toRegex(), " ").split("('.*?'|\".*?\"|\\S+)".toRegex())
                                )
                            ) == ParseResult.SUCCESS) return@run
                    }
                    println("No such command found")
            }
        }
    }

    override fun addParser(parser: CommandParser) {
        _parsers.add(parser)
    }

    override fun removeParser(parser: CommandParser) {
        _parsers.remove(parser)
    }

    private fun <T> safe(action: () -> T): T {
        return action()
    }
}