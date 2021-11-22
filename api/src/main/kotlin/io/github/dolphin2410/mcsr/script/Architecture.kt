package io.github.dolphin2410.mcsr.script

import io.github.dolphin2410.mcsr.util.OS

enum class Architecture {
    WINDOWS_64,
    WINDOWS_32,
    LINUX_64,
    LINUX_32,
    DARWIN_64,
    DARWIN_32,
    SOLARIS_64,
    SOLARIS_32,
    UNKNOWN_64,
    UNKNOWN_32;

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
    }
}