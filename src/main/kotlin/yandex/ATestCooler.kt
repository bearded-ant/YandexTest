package yandex

class ATestCooler {
    fun getTime(start: String, target: String, mode: String): String {
        var result = ""
        when (mode) {
            "auto" -> result = target
            "fan" -> result = start
            "freeze" -> {
                if (start > target) result = target
                if (start < target) result = start
                if (start == target) result = start
            }

            "heat" -> {
                if (start > target) result = start
                if (start < target) result = target
                if (start == target) result = start
            }
        }
        return result
    }
}