package src.Math;

public class ReverseInteger_7 {
    /*
    Given a signed 32-bit integer x, return x with its digits reversed.
    If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).



Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21


Constraints:

-231 <= x <= 231 - 1
    */

    public static void main(String[] args) {
        int[] tests = {1463847412, 1563847412, 1463847412, -1463847412, 0, 1534236469};
        for (int num : tests) {
            System.out.println("Test: " + num + "  Reversed: " + reverse(num));
        }
    }

    /**
     * 典型的考察边界意识题, 题不难, 重点是在看对边界意识的感知能力.
     * 题目要求返回int值, 这时如果反转过程中result溢出了int, 就会造成结果错误, 所以要在 * 10 之前就判断result是否超过界限.
     * 而且关键的关键是考虑到极限的边界, 也就是 2147483647 到 -2147483648 之间的, 超过1都不行.
     * 那么我们的判断条件就要写好了, 当result 大于了 MAX_INT的1/10, 当result * 10 之后就会越界. 第二个是如果result刚好等于MAX_INT的1/10
     * 那么result * 10 之后 加7 以上就会越界, 因为 MAX_INT 的最后一位是7. 对于负数也是一样的.
     */
    private static int reverse(int num) {
        int result = 0;

        while (num != 0) {
            int digit = num % 10; // 取个位数
            num /= 10; // 缩小十倍
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0; // 判断更新result后是否会溢出.
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0; // 判断更新result后是否会溢出.
            }
            result = result * 10 + digit; // 如果不溢出, 乘10再加个位数.
        }

        return result;

    }
}
