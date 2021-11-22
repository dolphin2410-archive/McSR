package io.github.dolphin2410.mcsr.util

enum class OS {
    WINDOWS,
    LINUX,
    MACOS,
    SOLARIS,
    UNKNOWN;

    companion object {
        @JvmStatic
        fun get(): OS {
            return with(System.getProperty("os.name").lowercase()) {
                when {
                    contains("win") -> WINDOWS
                    contains("mac") -> MACOS
                    contains("nix") || contains("nux") || contains("aix") -> LINUX
                    contains("sunos") -> SOLARIS
                    else -> UNKNOWN
                }
            }
        }
    }
}