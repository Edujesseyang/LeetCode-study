package src.DoublePointer;

public class ValidPalindromeAfterDeleteOneChar_680 {
    /*
    Given a string s, return true if the s can be palindrome after deleting at most one character from it.



Example 1:

Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
Example 3:

Input: s = "abc"
Output: false


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
    */

    public static void main(String[] args) {
        String[] test = {"ava", "eededed", "abca", "aaaaac"};
        for (int i = 0; i < test.length; i++) {
            System.out.println("Test " + (i + 1) + ": Output: " + validPalindrome(test[i]) + " Expected: true");
        }
    }

    /**
     * 这一题是双指针加一点点贪心, 首先是直接按照普通的左右针的循环去对两边,
     * 如果有两边不一样的情况, 做一个helper function分别对一次跳过左边的跳过右边的,
     * 其中用任何一边return了true, 就证明删了该边就可以变成palindrome.
     * 反之如果return了false, 则表面即使删了这个也不能变palindrome.
     */
    public static boolean validPalindrome(String s) {
        // define left/right pointers
        int left = 0, right = s.length() - 1;
        // check from both ends
        while (left < right) {
            // if equal, both sides move one to the center
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                // check if skip left/right can make the rest palindrome
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
        }
        return true; // return true if no needs to skip any char
    }

    /**
     * helper function
     */
    public static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
