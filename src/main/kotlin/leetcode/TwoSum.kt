package leetcode

class TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
//        for (i in 0 until nums.lastIndex) {
//            for (j in i + 1..nums.lastIndex) {
//                if (nums[i] + nums[j] == target)
//                    return intArrayOf(i, j)
//            }
//        }

        val map = mutableListOf<Int>()
        for (i in nums.indices) {
            val diff = target - nums[i]
            if (map.contains(diff) && map.indexOf(diff) != i) {
                return intArrayOf(i, map.indexOf(diff))
            }
            else map.add(i,nums[i])
        }
        return IntArray(2)
    }
}