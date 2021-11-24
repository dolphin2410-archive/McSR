package io.github.dolphin2410.mcsr.api.util

import java.io.InputStream
import java.net.URL

object ResourceManager {
    fun resource(clazz: Class<*>, file: String): URL {
        return clazz.classLoader.getResource(file) ?: clazz.getResource(file) ?: throw NullPointerException("File: $file. Not Found in Resources")
    }

    fun stream(clazz: Class<*>, file: String): InputStream {
        return resource(clazz, file).openStream()
    }
}