package io.github.dolphin2410.mcsr.api.script

enum class ScriptType(baseUrl: String) {
    DOLPHIN2410("https://github.com/dolphin2410/server-script/"),
    AROXU("https://github.com/aroxu/server-script/"),
    MONUN("https://raw.githubusercontent.com/monun/server-script/master/.server/start.sh");

    val link: ScriptLink = ScriptLink(baseUrl, this)
}