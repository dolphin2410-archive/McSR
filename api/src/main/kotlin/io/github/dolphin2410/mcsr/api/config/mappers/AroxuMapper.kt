package io.github.dolphin2410.mcsr.api.config.mappers

object AroxuMapper: Mapper {
    override fun map(key: String): String {
        return when(key) {
            "serverUrl" -> "server"
            "autoBackup" -> "backup"
            "autoReload" -> "restart"
            "jvmArgs" -> "jarArgs"
            else -> key
        }
    }
}