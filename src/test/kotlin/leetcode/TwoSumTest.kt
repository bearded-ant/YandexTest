package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TwoSumTest () {
    private val twoSum:TwoSum = TwoSum()

    @Test
    fun getSum2_7_11_15() {
        val expected1 = intArrayOf(0,1)
        val expected2 = intArrayOf(1,0)
        val result = twoSum.twoSum(intArrayOf(2,7,11,15), 9)
        assertTrue(expected1.contentEquals(result) || expected2.contentEquals(result))
    }
    @Test
    fun getSum3_2_4() {
        val expected1 = intArrayOf(1,2)
        val expected2 = intArrayOf(2,1)
        val result = twoSum.twoSum(intArrayOf(3,2,4), 6)
        assertTrue(expected1.contentEquals(result) || expected2.contentEquals(result))
    }
    @Test
    fun getSum3_3() {
        val expected1 = intArrayOf(0,1)
        val expected2 = intArrayOf(1,0)
        val result = twoSum.twoSum(intArrayOf(3,3), 6)
        assertTrue(expected1.contentEquals(result) || expected2.contentEquals(result))
    }
    @Test
    fun getSum0_3_3() {
        val expected1 = intArrayOf(1,2)
        val expected2 = intArrayOf(2,1)
        val result = twoSum.twoSum(intArrayOf(0,3,3), 6)
        assertTrue(expected1.contentEquals(result) || expected2.contentEquals(result))
    }
    @Test
    fun getSumNegative() {
        val expected1 = intArrayOf(2,4)
        val expected2 = intArrayOf(4,2)
        val result = twoSum.twoSum(intArrayOf(-1,-2,-3,-4,-5), -8)
        assertTrue(expected1.contentEquals(result) || expected2.contentEquals(result))
    }
}