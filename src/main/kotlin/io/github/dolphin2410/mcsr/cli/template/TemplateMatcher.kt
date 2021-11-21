package io.github.dolphin2410.mcsr.cli

object TemplateMatcher {

    /**
     * A pattern `"Hello, my name is {name} and I am {age} years old"` with
     * data `"Hello, my name is dolphin2410 and I am 14 years old"` will return
     * { "name" : "dolphin2410", "age": "14" } in Java HashMap
     */
    fun match(pattern: String, data: String): HashMap<String, String> {

    }
}