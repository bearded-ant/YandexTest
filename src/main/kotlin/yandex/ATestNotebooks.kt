package yandex

import kotlin.math.max

class ATestNotebooks {
    fun getSmall(a1: Int, b1: Int, a2: Int, b2: Int): Int {
        val s1 = max(a1, a2) * (b1 + b2)
        val s2 = max(b1, b2) * (a1 + a2)
        val s3 = max(a1, b2) * (a2 + b1)
        val s4 = max(a2, b1) * (a1 + b2)
        val allS = listOf(s1, s2, s3, s4).sorted()
        return allS[allS.lastIndex]
    }
}