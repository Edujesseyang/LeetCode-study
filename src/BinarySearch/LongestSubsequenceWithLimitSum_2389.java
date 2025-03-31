package src.BinarySearch;

import java.util.Arrays;

public class LongestSubsequenceWithLimitSum_2389 {
    /*
    * You are given an integer array nums of length n, and an integer array queries of length m.
    * Return an array answer of length m where answer[i] is the maximum size of a subsequence that
    * you can take from nums such that the sum of its elements is less than or equal to queries[i].

    * A subsequence is an array that can be derived from another array by
    * deleting some or no elements without changing the order of the remaining elements.



Example 1:

Input: nums = [4,5,2,1], queries = [3,10,21]
Output: [2,3,4]
Explanation: We answer the queries as follows:
- The subsequence [2,1] has a sum less than or equal to 3.
  It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
- The subsequence [4,5,1] has a sum less than or equal to 10.
  It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
- The subsequence [4,5,2,1] has a sum less than or equal to 21.
  It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.
Example 2:

Input: nums = [2,3,4,5], queries = [1]
Output: [0]
Explanation: The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.


Constraints:

n == nums.length
m == queries.length
1 <= n, m <= 1000
1 <= nums[i], queries[i] <= 106*/

    public static void main(String[] args) {
        int[] test = {4, 5, 2, 1};
        int[] queries = {3, 10, 21};

        int[] result = findLongestSubsequence(test, queries);
        for (int num : result) {
            System.out.print(num + ", ");
        }

    }

    /**
     * O(m + n) 利用 prefix sum 技巧来实现, 就是先建一个和 nums 一样长度的 array, 把nums第i位之前的总和放在新array的i位上面.
     * 比如 test = {4, 5, 2, 1}, sort后 = {1, 2, 4, 5}. 那么prefixSum = {1, 3, 7, 12};
     * 第一位是 1, 第二位是1 + 2, 第三位1 + 2 + 4, 第四位1 + 2 + 4 + 5.
     * 然后在这个array 中查找每一个queries中的数应该在的位置.
     * @param nums : int[] 给定的数列
     * @param queries : int[] 待查询的数们
     * @return : int[] 待查询数最多大于多少个给定数列中的数的总和
     */
    private static int[] findLongestSubsequence(int[] nums, int[] queries) {
        Arrays.sort(nums); // 先sort 给定数列
        int numsLen = nums.length;
        int queryLen = queries.length;

        int[] prefixSum = new int[numsLen]; // 建prefixSum array
        prefixSum[0] = nums[0]; // 初始化第一个
        for (int i = 1; i < numsLen; i++) { // 都没的逐次增加
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }


        int[] result = new int[queryLen]; // 建答案array
        for (int i = 0; i < queryLen; i++) {
            result[i] = binarySearch(prefixSum, queries[i]); // 二分查该数应该在哪个prefix array的位置中, 加到result里面
        }

        return result;
    }

    /**
     * 此二分查找很重要, 首先明确我们要找什么, 要找不大于target的数的数量,
     * 如{1, 2, 2, 2, 2, 3} target=3, 这里不大于3的一共有6个, 所以就return 6 也就是循环结束后的left位置
     * @param nums :
     * @param target :
     * @return :
     */
    private static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


}
