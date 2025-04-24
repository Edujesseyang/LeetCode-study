package src.Array;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement_169 {
    /*
    Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.



Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2


Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109


Follow-up: Could you solve the problem in linear time and in O(1) space?
    */
    public static void main(String[] args) {
        int[][] test = {{1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2,}, {1, 1, 2, 2, 1}, {3, 3, 3, 3, 3, 1, 1, 1, 2, 2}};
        int[] ans = {2, 1, 3};
        for (int i = 0; i < test.length; i++) {
            System.out.println("Test " + (i + 1) + " Output: " + getMajorityElement(test[i]) + ": Expected: " + ans[i]);
        }
    }

    /**
     * 经典算法, Boyer-Moore 投票法. 这种算法在各种找majority的问题中特别有用.
     * 此题的思路是:
     * 做一个候选人变量, 和一个计票变量, 在遍历array的过程中:
     * 1. 如果vote是零时, 将原candidate淘汰. 更新一个新的候选人.
     * 2. 如果遍历到的数字跟候选人相同, 则票数增加, 否则减少.
     * *
     * 时间复杂度O(n), 空间复杂度O(1)
     * 这个算法是抵消思维，每当遇到不同的元素，它们互相“削弱”。
     * 因为 多数元素数量 > 其他所有元素总和，它怎么抵消都还剩下。
     * *
     * 注意: 此算法一定是要有一个大于一半以上的数才可以.
     * 如果某数的总和刚好等于n/2 -1, 并且刚好在第一位,
     * 随着循环的进行他和不断地增加或减小, 当到最后一位的时候, 他刚好等于0, 因为他的数量比不同于他的总和刚好少一个,
     * 所以会在最后一位时vote = 0, 并且将最后一位数换上, 算法就失效了.
     * 这时就只能用HashMap的方法了.
     */
    private static int getMajorityElement(int[] nums) {
        int candidate = 0;
        int vote = 0;
        for (int num : nums) {
            if (vote == 0) {
                candidate = num;
            }
            vote += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * 另一种算法, 使用HashMap, 将array 的 freq 统计出来, 放在map中. 并且找到该map值最大的那个key.
     * 时间复杂度O(n), 空间复杂度O(n)
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int max = 0;
        int keyOfMax = 0;
        for (Integer key : freqMap.keySet()) {
            Integer val = freqMap.get(key);
            if (val > max) {
                max = val;
                keyOfMax = key;
            }
        }
        return keyOfMax;
    }

}
