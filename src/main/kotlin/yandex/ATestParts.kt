package yandex

class ATestParts {
    fun getAmount(n: Int, k: Int, m: Int): Int {

        if (n == 0 || k == 0 || m == 0) return 0
        if (m > k || k > n) return 0

        var residue = n
        var count = 0

        while ((residue / k) >= 1) {
            val kCount = residue / k
            val mCount = (k / m) * kCount
            residue -= mCount * m
            count += mCount
        }
        return count
    }
}