package src.HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestharmoniousSubsquence_594 {
    /*
    We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.

Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.



Example 1:

Input: nums = [1,3,2,2,5,2,3,7]

Output: 5

Explanation:

The longest harmonious subsequence is [3,2,2,2,3].

Example 2:

Input: nums = [1,2,3,4]

Output: 2

Explanation:

The longest harmonious subsequences are [1,2], [2,3], and [3,4], all of which have a length of 2.

Example 3:

Input: nums = [1,1,1,1]

Output: 0

Explanation:

No harmonic subsequence exists.



Constraints:

1 <= nums.length <= 2 * 10^4
-10^9 <= nums[i] <= 10^9
*/

    public static void main(String[] args) {
        int[] test = {1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println("Output: " + findSubsequence(test) + " Expected: 5");
    }

    /**
     * 这个是一个滑动窗口的解法, TC: O(NlogN),  SC: O(N)
     * 次优解, 关键是维护窗口, 第一是当左右差为1的时候统计, 不为1的时候调整左边框, 这是调整需要用while, 调整至直到左右差回到1为止.
     */
    private static int findSubsequence(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int left = 0, right = 0; right < nums.length; right++) {
            if (nums[right] - nums[left] == 1) { // 左右差为1时, 统计
                count = Math.max(count, right - left + 1);
            }
            while (nums[right] - nums[left] > 1) { // 左右差大于1时, 调整
                left++;
            }
        }
        return count;
    }

    /**
     * hashMap的解法, TC: O(N), SC: O(1)
     * 最优解, 这个解法的核心思路时将每个出现的数map在一个hashMap上, 其key为num本身, value为出现的次数
     * 然后在找出任何相邻的两个数中,两种出现的总次数最高的那段,
     * 比如 {1,2,2,2,3,3,4,5} 统计feq之后, 1:1, 2:2, 3:2, 4:1, 5:1 中里面任意相邻两数中, 2和3的出现次数总和最多, 所以2的频率加3的频率为解果.
     * 这feqMap的方法可以用在很多地方, 甚至可以用int[] 替代.
     */
    private static int findSubsequence2(int[] nums) {
        Map<Integer, Integer> feqMap = new HashMap<>();
        for (int num : nums) {
            // 此处, 加入map, key为各个数, 值为(如果该值存在则取其值, 不存在则为0) 然后加1
            // 相当于:
            // if(feqMap.containsKey(num)){
            //      value = feqMap.get(key);
            // } else {
            //      value = 0;
            // } 然后再加1.
            feqMap.put(num, feqMap.getOrDefault(num, 0) + 1);
        }
        // 初始化map完成后, 我们得到了一个拥有所有项的频率的feqMap
        int count = 0;
        for (int key : feqMap.keySet()) { // 遍历map的key
            if (feqMap.containsKey(key + 1)) { // 如果有一下项
                count = Math.max(count, feqMap.get(key) + feqMap.get(key + 1)); // 统计一个和下一项的频率总和, 并更新到答案
            }
        }
        return count;
    }
}
