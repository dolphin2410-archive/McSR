package io.github.dolphin2410.mcsr.api.util.wrapper

import kotlin.reflect.KProperty

open class Property<T> {
    protected open var data: T? = null

    fun set(value: T) {
        this.data = value
    }

    fun get(): T {
        return data!!
    }

    fun getSafe(): T? {
        return data
    }

    internal fun forceSet(value: Any) {
        @Suppress("unchecked_cast")
        this.data = value as T
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return get()
    }
}