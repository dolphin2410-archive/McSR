package io.github.dolphin2410.mcsr.api.util

import io.github.dolphin2410.mcsr.api.util.wrapper.Property

data class Resettable<T>(val init: ()->T): Property<T>() {
    override var data: T? = init()

    fun reset() {
        data = init()
    }
}