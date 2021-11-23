package io.github.dolphin2410.mcsr.api.cli

import io.github.dolphin2410.mcsr.api.cli.parser.ParseResult
import io.github.dolphin2410.mcsr.api.cli.template.TemplateMatcher
import java.util.*
import kotlin.collections.HashMap

class CommandArguments(private val initArgs: List<String>) {
    companion object {
        const val FLAG_PREFIX = "--"
        const val SHORT_FLAG_PREFIX = "-"
    }

    private val arguments = initArgs.filter { !it.startsWith(FLAG_PREFIX) && !it.startsWith(
        SHORT_FLAG_PREFIX
    ) }

    private val flags = initArgs.filter { it.startsWith(FLAG_PREFIX) || it.startsWith(
        SHORT_FLAG_PREFIX
    ) }

    @Suppress("WeakerAccess")
    var position: Int = 0
        private set

    private var commandSent = false

    val size: Int
        get() = arguments.size + flags.size

    @Suppress("WeakerAccess")
    fun flag(name: String, vararg alias: String): Boolean {
        return flags.contains(FLAG_PREFIX + name) || alias.any { flags.contains(
            SHORT_FLAG_PREFIX + it) }
    }

    fun noflag(name: String, vararg alias: String, then: CommandArguments.() -> Unit) {
        if (!flag(name, *alias)) then(this)
    }

    fun flag(name: String, vararg alias: String, then: CommandArguments.() -> Unit) {
        if (flag(name, *alias)) then(this)
    }

    fun argumentFlag(pattern: String, then: CommandArguments.(HashMap<String, String>) -> Unit) {
        val matching = flags.find { TemplateMatcher.doesMatch(pattern, it.removeSuffix("-").removeSuffix("-")) }
        if (matching != null) {
            then(this, TemplateMatcher.match(pattern, matching))
        }
    }

    fun input(then: CommandArguments.(String) -> Unit) {
        if (arguments.getOrNull(position) != null) {
            clone().apply { position += 1 }.run {
                then(this@run, arguments[position])
            }
        }
    }

    fun input(pattern: String, then: CommandArguments.(HashMap<String, String>) -> Unit) {
        val str = StringJoiner(" ").apply {
            val until = pattern.split("\\s+".toRegex()).size
            for (i in position until until) {
                add(arguments[i])
            }
            position += until
        }.toString()
        if (TemplateMatcher.doesMatch(pattern, str)) {
            then(
                this,
                TemplateMatcher.match(pattern, str)
            )
        }
    }

    @Suppress("WeakerAccess")
    fun option(vararg _args: String): Boolean {
        if (_args.isEmpty()) {
            return true
        }

        if (arguments.size <= position) {
            return false
        }

        for (i in position until _args.size) {
            if (_args[i] != arguments.getOrNull(i)) return false
        }

        return true
    }

    fun option(vararg _args: String, then: CommandArguments.() -> Unit) {
        if (option(*_args)) clone().apply { position += _args.size }.run(then)
    }

    fun command(vararg _args: String, then: CommandArguments.() -> Unit) {
        if (option(*_args)) clone().apply { position += _args.size }.run(then).run { commandSent = true }
    }

    fun parse(then: CommandArguments.() -> Unit): ParseResult {
        then(this)
        return if (commandSent) ParseResult.SUCCESS else ParseResult.FAIL
    }

    fun otherwise(then: CommandArguments.() -> Unit) {
        if (!commandSent) {
            then(this)
            commandSent = true
        }
    }

    private fun clone(): CommandArguments {
        return CommandArguments(initArgs)
    }
}