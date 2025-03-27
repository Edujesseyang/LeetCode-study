package src.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidPalindrome_125 {
    /*
    A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing
    all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include
    letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.



Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.


Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.*/

    public static void main(String[] args) {
        List<String> tests = new ArrayList<>(Arrays.asList("A man, a plan, a canal: Panama", "race a car", " ", "12321", "ab8a"));
        boolean[] answers = {true, false, true, true, false};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Test " + (i + 1) + ": Output: " + isVaildPalindrome(tests.get(i)) + " Expected: " + answers[i]);
        }
    }

    private static boolean isVaildPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) { // 直接用while 而不是 if 有助于一下跳过连续的符合
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /*
    关键点:
    1. 善用 Character 下的各自method. 如果不可使用, 自己建 helper function
    2. 可以判断完成后直接 return true, 因为如果 loop 中没有 return false, 则证明其是 palindrome.
     */


}
