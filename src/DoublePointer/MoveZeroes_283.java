package src.DoublePointer;

import java.util.Arrays;

public class MoveZeroes_283 {
    /*
    Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.



Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]


Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1


Follow up: Could you minimize the total number of operations done?
    */

    public static void main(String[] args) {
        test(new int[]{0, 1, 0, 3, 12}, "[1, 3, 12, 0, 0]");
        test(new int[]{0, 0, 0, 0}, "[0, 0, 0, 0]");
        test(new int[]{1, 2, 3, 4}, "[1, 2, 3, 4]");
        test(new int[]{1, 0, 2, 0, 3}, "[1, 2, 3, 0, 0]");
        test(new int[]{0}, "[0]");
        test(new int[]{5}, "[5]");
        test(new int[]{}, "[]");
        test(new int[]{0, 1, 0, 2, 0, 3, 0, 4}, "[1, 2, 3, 4, 0, 0, 0, 0]");
    }

    private static void test(int[] input, String expected) {
        moveZeroes(input);
        System.out.println("Output: " + Arrays.toString(input));
        System.out.println("Expect: " + expected);
        System.out.println("---------------------------");
    }

    /**
     * 经典的双指针移零的题,
     * 注意点:
     * 1. 并不用交换slow和fast, 分别用两个循环处理, 第一个循环以fast为准, 每次当fast不是零的时候, 就把slow改为fast值.
     * 2. 直到fast到达末尾位置, 这时候刚刚已经处理完所有的有效数字了, slow 后面的都是无效数字, 再吧slow循环到头, 所有数字改为0即可.
     * 时间复杂度绝对 O(n), 空间复杂度 O(1)
     */
    private static void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }
        // 慢指针位置不变, 往后的数字皆应为0.
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }
}
