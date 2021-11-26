package io.github.dolphin2410.mcsr.api.util.wrapper

class ObservableProperty<T>(val data: T) {

    private val _observers = ArrayList<PropertyObserver<T>>()

    fun trigger() {
        _observers.forEach {
            it.accept(data)
        }
    }

    fun addObserver(observer: PropertyObserver<T>) {
        _observers.add(observer)
    }

    fun removeObserver(observer: PropertyObserver<T>) {
        _observers.remove(observer)
    }

    fun clearObservers() {
        _observers.clear()
    }
}