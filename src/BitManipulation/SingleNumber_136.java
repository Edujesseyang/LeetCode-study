package src.BitManipulation;

public class SingleNumber_136 {
    /*
    Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.



Example 1:

Input: nums = [2,2,1]

Output: 1

Example 2:

Input: nums = [4,1,2,1,2]

Output: 4

Example 3:

Input: nums = [1]

Output: 1



Constraints:

1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Each element in the array appears twice except for one element which appears only once.
    */

    public static void main(String[] args) {
        int[] test = {54, 55, 56, 55, 54, 4, 4};
        System.out.println("Output: " + singleNumber(test) + " Expected:  56");
    }

    /**
     * 经典的不能再经典的异或运算的使用, ^ 为异或运算, 特点为 x ^ x = 0;   x ^ 0 = x.
     * 根据这个运算, 每次异或一个数, 只要他又异或了自己, 他最后就向最终结果贡献了 0;
     * 所以遍历一下, 异或每一个数字之后, 只要那些成双成对的数字, 最后都只向结果贡献了 0;
     * 只有那一个耍单的数字向结果贡献了它自己. 最后结果就等于它.
     * 前提是一定有一个数字是单独的, 一定其他的都是一对一对的.
     */
    private static int singleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) {
            result ^= n;
        }
        return result;
    }
}
