package io.github.dolphin2410.mcsr.cli.template

import java.text.MessageFormat
import java.text.ParseException
import kotlin.collections.HashMap

object TemplateMatcher {
    fun match(pattern: String, data: String): HashMap<String, String> {
        val vars = "\\{\\w+}".toRegex().findAll(pattern).map { it.value.removePrefix("{").removeSuffix("}") }.toList()
        var msgPattern = pattern
        vars.forEachIndexed { index, it ->
            msgPattern = msgPattern.replace(it, "$index")
        }

        return HashMap<String, String>().apply {
            MessageFormat(msgPattern).parse(data).forEachIndexed { index, it ->
                put(vars[index], it.toString())
            }
        }
    }

    fun doesMatch(pattern: String, data: String): Boolean {
        return try {
            match(pattern, data)
            true
        } catch (e: ParseException) {
            false
        }
    }
}