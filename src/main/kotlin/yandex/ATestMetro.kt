package yandex

import kotlin.math.max
import kotlin.math.min

class ATestMetro {
    fun getTimes(a: Int, b: Int, n: Int, m: Int): String {

        val minA = a * (n - 1) + n
        val minB = b * (m - 1) + m

        val maxA = minA + 2 * a
        val maxB = minB + 2 * b

        val minResult = max(minA, minB)
        val maxResult = min(maxA, maxB)

        if (maxResult < minResult) return "-1"

        return "$minResult $maxResult"
    }
}

