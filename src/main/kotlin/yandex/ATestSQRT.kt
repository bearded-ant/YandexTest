package yandex

import kotlin.math.sqrt

class ATestSQRT {
    fun exe(a: Int, b: Int, c: Int): String {
        if (c < 0) return "NO SOLUTION"
        if (b > 0 && c == 0) return "NO SOLUTION"
        if (a == 0 && c != sqrt(b.toDouble()).toInt()) return "NO SOLUTION"

        if ((a == 0 && b > 0) ||
            (a == 0 && b == 0 && c == 0) ||
            (a == 0 && b == 0 && c > 0)
        ) return "MANY SOLUTIONS"
//        (c^2 - b) / a
        return "${(c * c - b) / a}"
    }
}