package src.SlidingWindow;

public class MaxLenSubstringWithTwoOccurences_3090 {
    /*
    Given a string s, return the maximum length of a substring such that it contains at most two occurrences of each character.


Example 1:

Input: s = "bcbbbcba"

Output: 4

Explanation:

The following substring has a length of 4 and contains at most two occurrences of each character: "bcbbbcba".
Example 2:

Input: s = "aaaa"

Output: 2

Explanation:

The following substring has a length of 2 and contains at most two occurrences of each character: "aaaa".


Constraints:

2 <= s.length <= 100
s consists only of lowercase English letters.
    */

    public static void main(String[] args) {
        String test = "bcbbbcba";
        String test2 = "aaaa";
        System.out.println("Output: " + findMaxLen(test) + " Expected: 4");
        System.out.println("Output: " + findMaxLen(test2) + " Expected: 2");

    }

    /**
     * 此题为一个典型的滑动窗口题, 窗口长度非固定, 长度需要根据其中字母出现的频率来进行维护.
     * 主要逻辑为:
     * 建立一个字符频率表, 初始都为零
     * 然后进行遍历, 每次进入窗口的字符增加其频率
     * 判断一个当前字符频率是否大于2, 如大于2则
     * 持续更新左窗框, 直到该字符频率小于等于2.
     * 循环结束前, 结算窗口长度并更新至答案变量
     * @param s String
     * @return int
     */
    private static int findMaxLen(String s) {
        int[] freqMap = new int[256]; // 建频率映射

        int maxLen = 0; // 答案变量
        for (int i = 0, j = 0; i < s.length(); i++) {
            freqMap[s.charAt(i)]++; // 增加其频率
            while (freqMap[s.charAt(i)] > 2) { // 如果频率超过2
                freqMap[s.charAt(j++)]--; // 缩进左边直到频率小于等于2
            }
            maxLen = Math.max(maxLen, i - j + 1); // 结算长度并更新答案
        }
        return maxLen;
    }
}
