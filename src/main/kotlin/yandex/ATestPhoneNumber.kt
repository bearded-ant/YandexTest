package yandex

class ATestPhoneNumber {
    fun isEqual(newNumber: String, oldNumber: String): String {
        if (getFormatNumber(newNumber) == getFormatNumber(oldNumber)) return "YES"
        return "NO"
    }

    private fun getFormatNumber(number: String): String {
        var result: String = ""
        val formatNumber = number.replace("""^\+7""".toRegex(), "8").replace("[()-]".toRegex(), "")
        when (formatNumber.length) {
            11 -> result = formatNumber.substring(1)
            7 -> result = "495$formatNumber"
        }
        return result
    }
}