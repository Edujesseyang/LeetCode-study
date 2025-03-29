package src.HashMap;

import java.util.*;

public class TwoSum_1 {
    /*Given an array of integers nums and an integer target,

     return indices of the two numbers such that they add up to target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    You can return the answer in any order.



Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]


Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.


Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
*/

    public static void main(String[] args) {
        int[] test1 = {2, 7, 11, 15};
        int[] test2 = {3, 2, 4};
        int[] test3 = {3, 3};
        int[] test4 = {2, 7, 11, 15};
        int[] keys = {9, 7, 5, 18};
        int[][] result = {{0, 1}, {0, 2}, {0, 0}, {1, 2}};

        List<int[]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3, test4));
        for (int i = 0; i < result.length; i++) {
            int[] temp = twoSum(tests.get(i), keys[i]);
            System.out.println("Test " + (i + 1) + ": Output {" + temp[0] + ", " + temp[1] + "} Expected: {" + result[i][0] + ", " + result[i][1] + "}");
        }

    }


    /**
     * O(n)
     * @param nums : un-sorted int arr
     * @param target : int
     * @return : int[2] indexes of two integers, their sum = target
     */
    private static int[] twoSum(int[] nums, int target) {
        // 用一个 HashMap 去存和找每一个数, key=该数, value=该数的index
        Map<Integer, Integer> map = new HashMap<>();

        // 一个 for 循环整个nums
        for (int i = 0; i < nums.length; i++) {
            // 如果在 hashMap 中找到有 target 减去该数的 key
            if (map.containsKey(target - nums[i])) {
                // 则 return 一个map中找到的那个pair的value, 和i, 也就是两个indexes
                return new int[]{map.get(target - nums[i]), i};
            }
            // 如果没有则将该数和它的index组成pair, 并加入map.
            map.put(nums[i], i);
        }
        // 如果什么都没找到, return 一个0, 0 的array
        return new int[]{0, 0};
    }

    // LeetCode 第一题, 经典中的经典, HashMap的经典运用. 不能再经典了. 全文背诵!
}
