package src.Math;

public class PalindromeNumber_9 {
    /*
    Given an integer x, return true if x is a palindrome, and false otherwise.

Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.


Constraints:

-231 <= x <= 231 - 1


Follow up: Could you solve it without converting the integer to a string?
    */

    public static void main(String[] args) {

    }

    // 双指针解法
    private static boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        } else if (x < 0 || x % 10 == 0) {
            return false;
        }

        int len = (int) Math.floor(Math.log10(x)) + 1;
        int[] arr = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            arr[i] = x % 10;
            x /= 10;
        }

        int left = 0, right = len - 1;
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            } else {
                left++;
                right--;
            }
        }

        return true;
    }

    // reverse 对比解法
    private static boolean isPalindrome2(int x) {
        if (x == 0) {
            return true;
        } else if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reversed = 0;
        int xCopy = x;
        while (xCopy > 0) {
            reversed = reversed * 10 + xCopy % 10;
            xCopy /= 10;
        }
        return x == reversed;
    }

    // 优化版 reverse对比
    private static boolean isPalindrome3(int x) {
        if (x == 0) {
            return true;
        } else if (x < 0 || x % 10 == 0) {
            return false;
        }

        int reversed = 0;
        int xCopy = x;
        while (xCopy > reversed) {
            reversed = reversed * 10 + xCopy % 10;
            xCopy /= 10;
        }
        return xCopy == reversed || xCopy == reversed / 10;
    }
}
