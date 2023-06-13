package yandex

class ATestRectangle {
    fun isRectangle(a: Int, b: Int, c: Int): String {
        return if ((a + b > c) && (b + c > a) && (c + a > b)) "YES"
        else "NO"
    }
}