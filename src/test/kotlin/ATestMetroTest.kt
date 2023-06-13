import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import yandex.ATestMetro

class ATestMetroTest {

    private val metroTime: ATestMetro = ATestMetro()

    @Test
    fun getTimes1332() {
        val expected = "5 7"
        assertEquals(expected, metroTime.getTimes(1, 3, 3, 2))
    }

    @Test
    fun getTimes1512() {
        val expected = "-1"
        assertEquals(expected, metroTime.getTimes(1, 5, 1, 2))
    }

    @Test
    fun getTimes0() {
        val expected = "-1"
        assertEquals(expected, metroTime.getTimes(0, 3, 3, 2))
    }
    @Test
    fun getTimes02() {
        val expected = "-1"
        assertEquals(expected, metroTime.getTimes(3, 0, 3, 2))
    }
    @Test
    fun getTimes2510() {
        val expected = "1 5"
        assertEquals(expected, metroTime.getTimes(2, 5, 1, 0))
    }
    @Test
    fun getTimes2501() {
        val expected = "1 2"
        assertEquals(expected, metroTime.getTimes(2, 5, 0, 1))
    }
    @Test
    fun getTimes5201() {
        val expected = "1 5"
        assertEquals(expected, metroTime.getTimes(5, 2, 0, 1))
    }
}