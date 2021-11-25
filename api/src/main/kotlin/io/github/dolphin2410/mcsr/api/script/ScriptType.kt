package io.github.dolphin2410.mcsr.api.script

enum class ScriptType(baseUrl: String) {
    AROXU("https://github.com/aroxu/server-script/"),
    MONUN("https://raw.githubusercontent.com/monun/server-script/master/.server/start.sh");

    val link: ScriptLink = ScriptLink(baseUrl, this)
}