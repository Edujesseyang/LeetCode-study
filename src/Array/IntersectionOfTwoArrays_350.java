package src.Array;

import java.util.Arrays;

public class IntersectionOfTwoArrays_350 {
    /*
    Given two integer arrays nums1 and nums2, return an array of their intersection.
    Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.



Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.


Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000


Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
    */

    public static void main(String[] args) {
        int[] test1 = {1, 2, 2, 1};
        int[] test2 = {2, 2};
        int[] test3 = {4, 9, 5};
        int[] test4 = {9, 4, 9, 8, 4};

        System.out.print("Test 1: Output: ");
        print(findIntersectionOfArrays(test1, test2));
        System.out.println(" Expected: {2, 2}");

        System.out.print("Test 2: Output: ");
        print(findIntersectionOfArrays(test3, test4));
        System.out.println(" Expected: {4, 9}");
    }

    /**
     * 这intersection的题都可以往布尔映射或者是计数映射上面去想.
     * @param nums1 : unsorted
     * @param nums2 : unsorted
     * @return : int[]
     */
    private static int[] findIntersectionOfArrays(int[] nums1, int[] nums2) {
        int[] presentCount = new int[1001]; // 建立一个计数用的int[] map

        for (int num1 : nums1) { // update 这个map, 把每个nums1的数出现了几次都记录下来
            presentCount[num1]++;
        }

        int[] result = new int[Math.min(nums1.length, nums2.length)]; // 建立一个长度为较短input的答案array
        int result_i = 0; // 建立答案计数器

        for (int num2 : nums2) { // 遍历nums2
            if (presentCount[num2] > 0) { // 如果map中 num2 位置>0, 说明出现果至少一次
                result[result_i++] = num2; // 所以将num2 加入答案
                presentCount[num2]--; // 然后减少该位置的计数, 以免后面重复判定
            }
        }

        return Arrays.copyOf(result, result_i); // 最后将答案的正确数量生成array并return

    }

    private static void print(int[] nums) {
        if (nums.length == 0) {
            System.out.println("{ }");
            return;
        }
        System.out.print("{");
        for (int i = 0; i < nums.length - 1; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println(nums[nums.length - 1] + "}");
    }
}
