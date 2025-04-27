package src.Array;

import java.util.Arrays;

public class SpecialArrWithXElementsGreaterEqualToX_1608 {
    /*
    You are given an array nums of non-negative integers.

    nums is considered special if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.

Notice that x does not have to be an element in nums.

Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.



Example 1:

Input: nums = [3,5]
Output: 2
Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.
Example 2:

Input: nums = [0,0]
Output: -1
Explanation: No numbers fit the criteria for x.
If x = 0, there should be 0 numbers >= x, but there are 2.
If x = 1, there should be 1 number >= x, but there are 0.
If x = 2, there should be 2 numbers >= x, but there are 0.
x cannot be greater since there are only 2 numbers in nums.
Example 3:

Input: nums = [0,4,3,0,4]
Output: 3
Explanation: There are 3 values that are greater than or equal to 3.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
    */

    public static void main(String[] args) {
        int[] test = {3, 5};
        int[] test2 = {0, 4, 3, 0, 4};
        System.out.println(findXElementsGreaterEqualThanX(test));
        System.out.println(findXElementsGreaterEqualThanX(test2));
    }

    /**
     * 头痛问题: 保守方法:
     * sort一下, 然后loop, 每层loop 都count一个后面还有多少个数, 如果这个数大于等于nums[i]了, 则判断一下
     * 这是i如果时零, 表面后面的数都的数都大于一个不存在的数, 那么这个数就是后面的数量, 返回就行了.
     * 如果i不是零, 但是i前面的数时小于现在后面数的总量时, 表面这时后面的数总量等于这个i. 此时返回
     */
    private static int findXElementsGreaterEqualThanX(int[] nums) {

        Arrays.sort(nums);
        int numsLen = nums.length;

        for (int i = 0; i < numsLen; i++) {
            int numCount = numsLen - i;
            if (nums[i] >= numCount) {
                if (i == 0 || nums[i - 1] < numCount) {
                    return numCount;
                }
            }
        }
        return -1;
    }
}
