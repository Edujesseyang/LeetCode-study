package src.SlidingWindow;

public class MaxAvgSubarray_643 {
    /*
    You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
Any answer with a calculation error less than 10-5 will be accepted.



Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000


Constraints:

n == nums.length
1 <= k <= n <= 105
-104 <= nums[i] <= 104
    */

    public static void main(String[] args) {
        int[] test1 = {1, 12, -5, -6, 50, 3};
        int[] test2 = {-6662, 5432, -8558, -8935, 8731, -3083, 4115, 9931, -4006, -3284, -3024, 1714, -2825, -2374,
                -2750, -959, 6516, 9356, 8040, -2169, -9490, -3068, 6299, 7823, -9767, 5751, -7897, 6680, -1293, -3486,
                -6785, 6337, -9158, -4183, 6240, -2846, -2588, -5458, -9576, -1501, -908, -5477, 7596, -8863, -4088,
                7922, 8231, -4928, 7636, -3994, -243, -1327, 8425, -3468, -4218, -364, 4257, 5690, 1035, 6217, 8880,
                4127, -6299, -1831, 2854, -4498, -6983, -677, 2216, -1938, 3348, 4099, 3591, 9076, 942, 4571, -4200,
                7271, -6920, -1886, 662, 7844, 3658, -6562, -2106, -296, -3280, 8909, -8352, -9413, 3513, 1352, -8825};

        System.out.println("Test 1 (approach 1): Output = " + findMaxAvg(test1, 4) + " Expected: 12.75");
        System.out.println("Test 2 (approach 1): Output = " + findMaxAvg(test2, 90) + " Expected: 37.25555555555555");

        System.out.println("Test 1 (approach 2): Output = " + findMaxAvg(test1, 4) + " Expected: 12.75");
        System.out.println("Test 2 (approach 2): Output = " + findMaxAvg(test2, 90) + " Expected: 37.25555555555555");
    }


    /**
     * 固定长度的滑动窗口, 解法注意控制边界, 窗口内的操作, avg不用次次都求, 只需到窗口宽度等于k时求既可.
     * 初始化答案变量为负无穷, 因为这里要考虑负数.
     * @param nums unsorted int
     * @param k int, length of subArrays
     * @return double, max avg of each subArray
     */
    private static double findMaxAvg(int[] nums, int k) {
        double maxAvg = Double.NEGATIVE_INFINITY;
        int tempSum = 0;
        for (int left = 0, right = 0; right < nums.length; right++) { // 初始左右边
            tempSum += nums[right]; // 得出临时窗口的sum

            if (right - left == k - 1) { // 如果宽度达到k,则..... (另: 把 1 放在k的这边逻辑感更强)
                maxAvg = Math.max(maxAvg, tempSum / (double) k); // 更新一下答案变量
                tempSum -= nums[left++]; // 踢出左边, 以便维持窗口宽度.
            }
        }
        return maxAvg;
    }

    /**
     * 更加有"窗口感"的一个解法, 这里主要想法是单起一个循环来先初始化tempSum和maxAvg,
     * 此方法更加推荐, 因为避免了使用NEGATIVE_INFINITY之类的常量, 而且逻辑更加直观, 对两边框的维护也更容易理解.
     * 另外, 这种先初始化第一个窗口状态,然后再"滑动"剩余部分. 这样的写法特别适合固定大小的求和类的滑动窗口题. 严重推荐!
     * @param nums unsorted int arr
     * @param k int
     * @return double
     */
    private static double findMaxAvg2(int[] nums, int k) {
        int tempSum = 0;
        for (int i = 0; i < k; i++) {
            tempSum += nums[i]; // 求出第一个 k长的subArray的sum
        }
        double maxAvg = tempSum / (double) k; // 初始化答案变量为第一个k长的subArray的平均值

        for (int i = k; i < nums.length; i++) { // 滑动剩下的部分
            tempSum += nums[i] - nums[i - k]; // 维护窗内数据, 加入新进的右边并减去离开的左边
            maxAvg = Math.max(maxAvg, tempSum / (double) k); // 更新答案变量
        }
        return maxAvg;
    }
}
