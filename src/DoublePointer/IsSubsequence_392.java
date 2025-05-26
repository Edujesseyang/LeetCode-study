package src.DoublePointer;

public class IsSubsequence_392 {
    /*
Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string
by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
(i.e., "ace" is a subsequence of "abcde" while "aec" is not).



Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false


Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.


Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109,
and you want to check one by one to see if t has its subsequence. In this scenario,
how would you change your code?
    */

    public static void main(String[] args) {
        String target = "I love coding.";
        String test1 = "love";
        String test2 = "cid";
        String test3 = "Iv i";

        System.out.println("Test: " + target);
        System.out.println("target: " + test1 + " Output: " + isSubsequence(test1, target));
        System.out.println("target: " + test2 + " Output: " + isSubsequence(test2, target));
        System.out.println("target: " + test3 + " Output: " + isSubsequence(test3, target));

    }


    /*
     思路说明：
        从前往后扫描两个字符串。
        如果 s[i] == t[j]，说明当前字符匹配，i 右移。
        无论是否匹配，j 总要右移（因为我们要在 t 中找下一个可能的匹配）。
        最终，如果 i 走到了 s.length()，说明整个 s 都找到了匹配，是 t 的子序列。
     */
    private static boolean isSubsequence(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int sLen = sArr.length, tLen = tArr.length;
        int i = 0, j = 0;

        while (i < sLen && j < tLen) {
            if (sArr[i] == tArr[j]) {
                i++;
            }
            j++;
        }

        return i == sLen;
    }
}
