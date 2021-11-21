package io.github.dolphin2410.mcsr.cli

import io.github.dolphin2410.mcsr.settings.DefaultSettings.FLAG_PREFIX
import io.github.dolphin2410.mcsr.cli.parser.ParseResult
import io.github.dolphin2410.mcsr.cli.template.TemplateMatcher

class CommandArguments(private val initArgs: List<String>) {

    // FIXME Better Algorithm needed
    private val arguments = initArgs.filter { !it.startsWith(FLAG_PREFIX) }

    // FIXME Better Algorithm needed
    private val flags = initArgs.filter { it.startsWith(FLAG_PREFIX) }

    @Suppress("WeakerAccess")
    var position: Int = 0
        private set

    private var commandSent = false

    val size: Int
        get() = arguments.size + flags.size

    @Suppress("WeakerAccess")
    fun flag(name: String): Boolean {
        return flags.contains(FLAG_PREFIX + name)
    }

    fun noflag(name: String, then: CommandArguments.() -> Unit) {
        if (!flag(name)) then()
    }

    fun flag(name: String, then: CommandArguments.() -> Unit) {
        if (flag(name)) then()
    }

    fun inp(then: CommandArguments.(String) -> Unit) {
        if (arguments.getOrNull(position) != null) {
            clone().apply { position += 1 }.run {
                then(this@run, arguments[position])
            }
        }
    }

    fun inp(pattern: String, then: CommandArguments.(HashMap<String, String>) -> Unit) {
        then(
            TemplateMatcher.match(pattern, arguments[position++]).onEach {
                println(it.key)
                println(it.value)
            }
        )
    }

    @Suppress("WeakerAccess")
    fun args(vararg _args: String): Boolean {
        if (arguments.size <= position) {
            return false
        }

        for (i in position until _args.size) {
            if (_args[i] != arguments.getOrNull(i)) return false
        }

        return true
    }

    fun args(vararg _args: String, then: CommandArguments.() -> Unit) {
        if (args(*_args)) clone().apply { position += _args.size }.run(then)
    }

    fun command(vararg _args: String, then: CommandArguments.() -> Unit) {
        if (args(*_args)) clone().apply { position += _args.size }.run(then).run { commandSent = true }
    }

    fun parse(then: CommandArguments.() -> Unit): ParseResult {
        then()
        return if (commandSent) ParseResult.SUCCESS else ParseResult.FAIL
    }

    private fun clone(): CommandArguments {
        return CommandArguments(initArgs)
    }
}