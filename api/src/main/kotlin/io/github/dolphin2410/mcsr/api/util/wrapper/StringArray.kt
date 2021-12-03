package io.github.dolphin2410.mcsr.api.util.wrapper

class StringArray(@Suppress("WeakerAccess") val array: Array<String>) {

    val size: Int
        get() { return array.size }

    operator fun get(index: Int): String {
        return array[index]
    }

    operator fun set(index: Int, value: String) {
        array[index] = value
    }

    fun toList(): List<String> {
        return array.toList()
    }

    fun toMutableList(): List<String> {
        return array.toMutableList()
    }

    override fun toString(): String {
        return toList().joinToString()
    }
}