package io.github.dolphin2410.mcsr.api.script

import io.github.dolphin2410.mcsr.api.util.OS

enum class Architecture(vararg val aliases: String) {
    WINDOWS_64("windows", "win", "win64", "windows64", "win_64"),
    WINDOWS_32("win32", "windows32", "win_32"),
    LINUX_64("linux", "linux64"),
    LINUX_32("linux32"),
    DARWIN_64("darwin", "darwin64", "mac", "macos", "mac_64", "mac64", "macos64", "macos_64"),
    DARWIN_32("darwin32", "mac_32", "mac32", "macos32", "macos_32"),
    SOLARIS_64("solaris", "solaris64"),
    SOLARIS_32("solaris32"),
    UNKNOWN_64("unknown64"),
    UNKNOWN_32("unknown32");

    companion object {
        @JvmStatic
        fun get(): Architecture {
            val is64 = System.getProperty("os.arch").endsWith("64")
            return when (OS.get()) {
                OS.WINDOWS -> if (is64) WINDOWS_64 else WINDOWS_32
                OS.LINUX -> if (is64) LINUX_64 else LINUX_32
                OS.MACOS -> if (is64) DARWIN_64 else DARWIN_32
                OS.SOLARIS -> if (is64) SOLARIS_64 else SOLARIS_32
                OS.UNKNOWN -> if (is64) UNKNOWN_64 else UNKNOWN_32
            }
        }

        @JvmStatic
        fun get(name: String): Architecture {
            return values().find { it.name.lowercase() == name || it.aliases.contains(name) } ?: get()
        }
    }
}