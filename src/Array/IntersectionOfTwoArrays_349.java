package src.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays_349 {
    /*
    Given two integer arrays nums1 and nums2, return an array of their intersection.
    Each element in the result must be unique and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
    */

    public static void main(String[] args) {
        int[] test1 = {1, 2, 2, 1};
        int[] test2 = {2, 2};
        int[] test3 = {4, 9, 5};
        int[] test4 = {9, 4, 9, 8, 4};

        System.out.println("Approach 1: ");
        print(findIntersectionBooleanApproach(test1, test2));
        print(findIntersectionBooleanApproach(test3, test4));

        System.out.println("Approach 2: ");
        print(findIntersectionSetApproach(test1, test2));
        print(findIntersectionSetApproach(test3, test4));

    }


    private static void print(int[] nums) {
        System.out.print("{");
        for (int num : nums) {
            System.out.print(num + ", ");
        }
        System.out.println("}");
    }

    /**
     * 这个方法是布尔映射法, 对于已知长度的题非常适合, 如果未知长度可用HashMap或HashSet替代.
     * 时间复杂度为 O(n + m), 空间复杂度为一个1001位的boolean array. 算是O(1) 固定大小.
     * @param nums1 : int[]
     * @param nums2 : int[]
     * @return : int[]
     */
    private static int[] findIntersectionBooleanApproach(int[] nums1, int[] nums2) {
        boolean[] map = new boolean[1001]; // 一个固定大小的boolean array, 初始全是false
        int minLen = Math.min(nums1.length, nums2.length);
        int[] result = new int[minLen]; // 答案不会超过最小的那个input array
        int resultLen = 0; // 用一个计数器计算一共有多少个result

        for (int num1 : nums1) { // 把nums1 的这些位置改为true
            map[num1] = true;
        }

        for (int num2 : nums2) {
            if (map[num2]) { // 如果nums2 的这些位置有那个是true, 就说明重复了, 再改回false
                result[resultLen++] = num2;
                map[num2] = false;
            }
        }
        return Arrays.copyOf(result, resultLen); //return一个只有需要长度的copy
    }

    /**
     * 这个方法利用了set的去重特性, 时间复杂度为O(n + m) 空间复杂度为O(n + k) , k为重复项.
     * 也很效率, 只是hashset 有可能出现hash冲突, 不恨稳定.
     * @param nums1 : int[]
     * @param nums2 : int[]
     * @return : int[]
     */
    private static int[] findIntersectionSetApproach(int[] nums1, int[] nums2) {
        Set<Integer> compareSet = new HashSet<>(nums1.length); // 建一个set
        for (int num1 : nums1) { // 把nums1整个加入进去
            compareSet.add(num1);
        }

        int minLen = Math.min(nums1.length, nums2.length); // 找出稍小的array长度
        Set<Integer> result = new HashSet<>(minLen); // 建一个set接result
        for (int num2 : nums2) { // 如果nums2的数字再set种出现, 加入result
            if (compareSet.contains(num2)) {
                result.add(num2);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray(); // 转换set为array并放回
    }
}
