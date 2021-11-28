package io.github.dolphin2410.mcsr.api.util.wrapper

import java.util.*

enum class LanguageType(val isoName: String) {
    KOREAN("ko-KR"),
    ENGLISH("en-US");

    companion object {
        @JvmStatic
        var language: LanguageType = infer()

        @JvmStatic
        fun infer(): LanguageType {
            return when (Locale.getDefault()) {
                Locale.KOREAN -> KOREAN
                else -> ENGLISH
            }
        }
    }
}