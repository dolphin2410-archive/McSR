package io.github.dolphin2410.mcsr.cli

import io.github.dolphin2410.mcsr.cli.parser.CommandParser
import io.github.dolphin2410.mcsr.cli.parser.DefaultParser
import io.github.dolphin2410.mcsr.cli.parser.ParseResult
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.InvocationTargetException

class CLIManager {
    private val _parsers = ArrayList<CommandParser>()

    val parsers: Array<CommandParser>
        get() = _parsers.toTypedArray()

    suspend fun start() = coroutineScope {
        while (true) {
            kotlin.run {
                    print("> ")
                    val reader = BufferedReader(InputStreamReader(System.`in`))
                    val cmd = withContext(Dispatchers.IO) { safe(reader::readLine) }
                    _parsers.forEach {
                        if (it.parse(CommandArguments(cmd.split("\\s+".toRegex()))) == ParseResult.SUCCESS) {
                            return@run
                        }
                    }
                    println("No such command found")
            }
        }
    }

    fun addParser(parser: CommandParser) {
        _parsers.add(parser)
    }

    fun removeParser(parser: CommandParser) {
        _parsers.remove(parser)
    }

    private fun <T> safe(action: () -> T): T {
        return action()
    }
}