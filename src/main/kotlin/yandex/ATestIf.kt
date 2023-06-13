package yandex

class ATestIf {

    fun isThrow(a: Int, b: Int, c: Int, d: Int, e: Int): String {
        val hole = mutableListOf<Int>(d, e).sorted()
        val brick = mutableListOf<Int>(a, b, c).sorted()
        if (brick[0] <= hole[0] && brick[1] <= hole[1])
            return "YES"
        return "NO"
    }
}