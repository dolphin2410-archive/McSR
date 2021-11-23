package io.github.dolphin2410.mcsr.cli

import io.github.dolphin2410.mcsr.cli.parser.ParseResult
import io.github.dolphin2410.mcsr.cli.template.TemplateMatcher
import java.util.*
import kotlin.collections.HashMap

class CommandArguments(private val initArgs: List<String>) {
    companion object {
        const val FLAG_PREFIX = "--"
    }

    // FIXME Better Algorithm needed
    private val arguments = initArgs.filter { !it.startsWith(io.github.dolphin2410.mcsr.cli.CommandArguments.Companion.FLAG_PREFIX) }

    // FIXME Better Algorithm needed
    private val flags = initArgs.filter { it.startsWith(io.github.dolphin2410.mcsr.cli.CommandArguments.Companion.FLAG_PREFIX) }

    @Suppress("WeakerAccess")
    var position: Int = 0
        private set

    private var commandSent = false

    val size: Int
        get() = arguments.size + flags.size

    @Suppress("WeakerAccess")
    fun flag(name: String): Boolean {
        return flags.contains(io.github.dolphin2410.mcsr.cli.CommandArguments.Companion.FLAG_PREFIX + name)
    }

    fun noflag(name: String, then: io.github.dolphin2410.mcsr.cli.CommandArguments.() -> Unit) {
        if (!flag(name)) then()
    }

    fun flag(name: String, then: io.github.dolphin2410.mcsr.cli.CommandArguments.() -> Unit) {
        if (flag(name)) then()
    }

    fun input(then: io.github.dolphin2410.mcsr.cli.CommandArguments.(String) -> Unit) {
        if (arguments.getOrNull(position) != null) {
            clone().apply { position += 1 }.run {
                then(this@run, arguments[position])
            }
        }
    }

    fun input(pattern: String, then: io.github.dolphin2410.mcsr.cli.CommandArguments.(HashMap<String, String>) -> Unit) {
        val str = StringJoiner(" ").apply {
            val until = pattern.split("\\s+".toRegex()).size
            for (i in position until until) {
                arguments[i]
            }
            position += until
        }.toString()
        if (TemplateMatcher.doesMatch(pattern, str)) {
            then(
                TemplateMatcher.match(pattern, str)
            )
        }
    }

    @Suppress("WeakerAccess")
    fun option(vararg _args: String): Boolean {
        if (arguments.size <= position) {
            return false
        }

        for (i in position until _args.size) {
            if (_args[i] != arguments.getOrNull(i)) return false
        }

        return true
    }

    fun option(vararg _args: String, then: io.github.dolphin2410.mcsr.cli.CommandArguments.() -> Unit) {
        if (option(*_args)) clone().apply { position += _args.size }.run(then)
    }

    fun command(vararg _args: String, then: io.github.dolphin2410.mcsr.cli.CommandArguments.() -> Unit) {
        if (option(*_args)) clone().apply { position += _args.size }.run(then).run { commandSent = true }
    }

    fun parse(then: io.github.dolphin2410.mcsr.cli.CommandArguments.() -> Unit): ParseResult {
        then()
        return if (commandSent) ParseResult.SUCCESS else ParseResult.FAIL
    }

    private fun clone(): io.github.dolphin2410.mcsr.cli.CommandArguments {
        return io.github.dolphin2410.mcsr.cli.CommandArguments(initArgs)
    }
}