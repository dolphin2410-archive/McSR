package io.github.dolphin2410.mcsr.cli.template

object TemplateMatcher {
    private const val DELIMITER = "/"

    fun match(pattern: String, data: String): HashMap<String, String> {
        val hashMap = HashMap<String, Int>()
        var lastIndex = 0
        val arr = ArrayList<Int>()
        val matchResult = ("\\{\\w+}".toRegex().findAll(pattern))
        matchResult.forEach {
            val varName = it.value.removePrefix("{").removeSuffix("}")
            if (hashMap[varName] == null) {
                hashMap[varName] = lastIndex++
            }
            arr.add(hashMap[varName]!!)
        }

        var index = 0

        // {a} + {b} - {a} = {b} will be replaced as {0} + {1} - {0} = {1}
        val replaced = "\\{\\w+}".toRegex().replace(pattern) {
            arr[index++].toString()
        }

        val split = replaced.split("\\{\\w+}".toRegex())

        val toReturn = HashMap<String, String>()

        var a = data
        split.forEach {
            a = a.replaceFirst(it, DELIMITER)
        }

        val reverse = HashMap<Int, String>()

        a.split(DELIMITER).forEachIndexed { i, it ->
            if (reverse[arr[i]] == null) {
                reverse[arr[i]] = it
            } else {
                if (reverse[arr[i]] != it) {
                    throw RuntimeException("Mismatch of same variable")
                }
            }
        }
        return HashMap<String, String>().apply {
            reverse.forEach {
                put(hashMap.entries.find { f -> f.value == it.key }!!.key, it.value)
            }
        }
    }
}