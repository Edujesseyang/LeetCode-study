package src.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindFirstPalindromicStringInArray_2108 {
    /*Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".

A string is palindromic if it reads the same forward and backward.



Example 1:

Input: words = ["abc","car","ada","racecar","cool"]
Output: "ada"
Explanation: The first string that is palindromic is "ada".
Note that "racecar" is also palindromic, but it is not the first.
Example 2:

Input: words = ["notapalindrome","racecar"]
Output: "racecar"
Explanation: The first and only string that is palindromic is "racecar".
Example 3:

Input: words = ["def","ghi"]
Output: ""
Explanation: There are no palindromic strings, so the empty string is returned.


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists only of lowercase English letters.*/

    public static void main(String[] args) {
        String[] test1 = {"abc", "car", "ada", "racecar", "cool"};
        String[] test2 = {"a", "notapalindrome", "racecar"};
        String[] test3 = {"notapalindrome", "racecar"};

        List<String[]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3));
        String[] answer = {"ada", "a", "racecar"};

        for (int i = 0; i < answer.length; i++) {
            System.out.println("Test " + (i + 1) + ": output: " + findFirstPalindromicString(tests.get(i)) + " expected: " + answer[i]);
        }
    }

    private static String findFirstPalindromicString(String[] words) {
        for (String s : words) {
            if (isPalindromicString(s)) {
                return s;
            }
        }
        return "";
    }

    private static boolean isPalindromicString(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /*
    关键点:
    当check left 和 right 是否相等的时候, 只要不相等就可以return false 了, 当 达到 !(left < right) 时, 就都是true.
     */


}
