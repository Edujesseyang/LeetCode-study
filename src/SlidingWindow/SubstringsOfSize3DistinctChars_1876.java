package src.SlidingWindow;

import java.util.Arrays;

public class SubstringsOfSize3DistinctChars_1876 {
    /*
    A string is good if there are no repeated characters.

Given a string s, return the number of good substrings of length three in s.

Note that if there are multiple occurrences of the same substring, every occurrence should be counted.

A substring is a contiguous sequence of characters in a string.



Example 1:

Input: s = "xyzzaz"
Output: 1
Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz".
The only good substring of length 3 is "xyz".
Example 2:

Input: s = "aababcabc"
Output: 4
Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
The good substrings are "abc", "bca", "cab", and "abc".


Constraints:

1 <= s.length <= 100
s consists of lowercase English letters.
    */

    public static void main(String[] args) {
        String test1 = "xyzzaz";
        String test2 = "aababcabc";
        System.out.println("Test 1: " + test1 + "  Output: " + findSubstrings(test1) + " Expected: 1");
        System.out.println("Test 2: " + test2 + "  Output: " + findSubstrings(test2) + " Expected: 4");
    }

    /**
     * 自己想出来的精妙解法, 很自豪!
     * 关键几点, 第一还是滑动窗口的维护问题, 这一题是固定三位, 所以窗左右相差一定是2.
     * 第二, 精妙的点, 如果任意三个字符排列, 只要最左边和最右边的feq都为1, 那么这三个字符绝对不重复.
     * 所以,我们只需要关注左边和右边的两个字符的feq即可, 窗口维护很简单, 右入窗, 判断, 然后左出窗.
     * @param s String
     * @return int
     */
    private static int findSubstrings(String s) {
        char[] sArr = s.toCharArray(); // 转成char[], 检索加速
        int[] feqMap = new int[256]; // 建立feqMap
        int count = 0; // 建立计数变量
        for (int l = 0, r = 0; r < sArr.length; r++) { // 左右针初始指零
            feqMap[sArr[r]]++; // 开始就将右的feq更新了. 右入窗时既更新feq
            if (r - l == 2) { // 当窗口宽带固定为二时
                if (feqMap[sArr[l]] == 1 && feqMap[sArr[r]] == 1) { // 如果左边框和右边框的feq都为1, 意味着三个不重复
                    count++; // 增加计数
                }
                feqMap[sArr[l++]]--; // 左出框, 缩减feq并增加左指针
            }
        }
        return count;
    }
}
