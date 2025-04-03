package src.SlidingWindow;

import java.util.Arrays;

public class MinDiffBetweenHighesetAndLowestInKScores_1984 {
    /*
    You are given a 0-indexed integer array nums, where nums[i] represents the score of the ith student. You are also given an integer k.

Pick the scores of any k students from the array so that the difference between the highest and the lowest of the k scores is minimized.

Return the minimum possible difference.



Example 1:

Input: nums = [90], k = 1
Output: 0
Explanation: There is one way to pick score(s) of one student:
- [90]. The difference between the highest and lowest score is 90 - 90 = 0.
The minimum possible difference is 0.
Example 2:

Input: nums = [9,4,1,7], k = 2
Output: 2
Explanation: There are six ways to pick score(s) of two students:
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 4 = 5.
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 1 = 8.
- [9,4,1,7]. The difference between the highest and lowest score is 9 - 7 = 2.
- [9,4,1,7]. The difference between the highest and lowest score is 4 - 1 = 3.
- [9,4,1,7]. The difference between the highest and lowest score is 7 - 4 = 3.
- [9,4,1,7]. The difference between the highest and lowest score is 7 - 1 = 6.
The minimum possible difference is 2.


Constraints:

1 <= k <= nums.length <= 1000
0 <= nums[i] <= 105
    */

    public static void main(String[] args) {
        int[] test = {9, 4, 1, 7};
        System.out.println("Output: " + findMinDiff(test, 2) + " Expected: " + 2);
    }

    /**
     * 这是一个简单的经典滑动窗口题, 窗口永远保持k个数量, 因此简单,
     * 为什么要保持k呢, 因为在sort之后的arr中, 在任取长度的subarray中, 最大的都是最后一个, 最小的都是第一个.
     * 所以保证窗口用为k个长度, 就可以. 这里不用可以维护窗户的两端, 只要初始化两个pointer的间距为k, 然后每次同时都++ 就可以了.
     * 要保证快指针到最后一位时结束, 注意边界控制.
     * @param nums unsorted int arr
     * @param k int
     * @return int
     */
    private static int findMinDiff(int[] nums, int k) {
        // 建立一个答案变量, 如果k=0时, 那么任何数和他自己的差都是零, 所以这里也可以之间return 0
        // 如果k非零, 则将k设为MAX
        int min = k == 0 ? 0 : Integer.MAX_VALUE;
        Arrays.sort(nums); // sort array
        // 循环体, 设双指针一个为0, 另一个为相聚k位的位置. 注意这里尽量保证让leading pointer自带增加, 手动增加following pointer, 减少越界风险.
        for (int left = 0, right = k - 1; right < nums.length; right++) { // right 为 leading
            min = Math.min(min, nums[right] - nums[left++]); // 更新min
        }
        return min;
    }
}
