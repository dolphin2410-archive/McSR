package io.github.dolphin2410.mcsr.api.config.mappers

object Dolphin2410Mapper: Mapper {
    override fun map(key: String): String {
        return when(key) {
            "serverUrl" -> "server"
            "autoBackup" -> "backup"
            "autoReload" -> "restart"
            "jvmArgs" -> "jvm_args"
            else -> key
        }
    }
}