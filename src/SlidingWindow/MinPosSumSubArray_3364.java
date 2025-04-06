package src.SlidingWindow;

public class MinPosSumSubArray_3364 {
    /*
    You are given an integer array nums and two integers l and r.
    Your task is to find the minimum sum of a subarray whose size is between l and r (inclusive) and whose sum is greater than 0.

Return the minimum sum of such a subarray. If no such subarray exists, return -1.

A subarray is a contiguous non-empty sequence of elements within an array.



Example 1:

Input: nums = [3, -2, 1, 4], l = 2, r = 3

Output: 1

Explanation:

The subarrays of length between l = 2 and r = 3 where the sum is greater than 0 are:

[3, -2] with a sum of 1
[1, 4] with a sum of 5
[3, -2, 1] with a sum of 2
[-2, 1, 4] with a sum of 3
Out of these, the subarray [3, -2] has a sum of 1, which is the smallest positive sum. Hence, the answer is 1.

Example 2:

Input: nums = [-2, 2, -3, 1], l = 2, r = 3

Output: -1

Explanation:

There is no subarray of length between l and r that has a sum greater than 0. So, the answer is -1.

Example 3:

Input: nums = [1, 2, 3, 4], l = 2, r = 4

Output: 3

Explanation:

The subarray [1, 2] has a length of 2 and the minimum sum greater than 0. So, the answer is 3.



Constraints:

1 <= nums.length <= 100
1 <= l <= r <= nums.length
-1000 <= nums[i] <= 1000
    */

    public static void main(String[] args) {
        int[] test = {3, -2, 1, 4};
        System.out.println("Output: " + findMinSum(test, 2, 3) + " Expected: 1");
    }

    /**
     * 此题并不是一个典型的滑动窗口, 因为题目要求, 窗口长度在r和l之间, 所以窗口的长度很难维护.
     * 这里的解法是暴力遍历subArray并求和, 时间复杂度近乎O(n^2). 有更优解前缀和, 可以去该区寻找.
     * 这里暴力遍历的思路很直接, 首先遍历整个数组, 在里面做小loop遍历subArray.
     * 关键的点在于如何控制左右边界. 首先小loop的长度不超过窗口的上限, 并在loop里持续求和.
     * 在符合两个条件时结算答案变量:
     * 1. 窗口长度大于要求下限.
     * 2. 此时sum为正数.
     * 此题比较烦人的点有两个, 除了长度难以维护外, 由于对正数的要求, sum值也无法用一个变量做加减控制, 所以滑窗技巧应用多有限制.
     */
    private static int findMinSum(int[] nums, int l, int r) {
        int n = nums.length; // 记录长度
        int minSum = Integer.MAX_VALUE; // 初始化答案

        for (int left = 0; left < n; left++) { // 遍历整个数组
            int sum = 0; // 建立临时性的sum
            for (int right = left; right < Math.min(n, left + r); right++) { // 遍历"窗口"内的subarray
                sum += nums[right]; // 更新sum值

                int subLen = right - left + 1; // 统计目前窗口长度
                if (subLen >= l) { // 如果窗口长度符合要求
                    if (sum <= 0) { // 如果此时sum不为正, 跳过此圈
                        continue;
                    }
                    minSum = Math.min(minSum, sum); // 结算答案变量
                }
            }
        }
        return minSum == Integer.MAX_VALUE ? -1 : minSum; // 如果答案变量完全没有被更新, 则return -1
    }
}