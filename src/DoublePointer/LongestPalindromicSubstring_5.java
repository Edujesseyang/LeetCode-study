package src.DoublePointer;

public class LongestPalindromicSubstring_5 {
    /*
    Given a string s, return the longest palindromic substring in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
    */

    public static void main(String[] args) {
        String test = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";

        System.out.println("Test approach 1: ");
        System.out.println("Output: " + longestPalindrome(test));
        System.out.println("Expected: ranynar");

        System.out.println("Test approach 2: ");
        System.out.println("Output: " + longestPalindrome2(test));
        System.out.println("Expected: ranynar");
    }

    /**
     * 这个是暴力枚举法,  时间复杂度O(n^3). 不推荐.
     */
    private static String longestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int fullLen = arr.length;
        int subLen = fullLen;
        while (subLen > 1) {
            for (int i = 0; i <= fullLen - subLen; i++) {
                if (isPalindrome(arr, i, i + subLen - 1)) {
                    return new String(arr, i, subLen);
                }
            }
            subLen--;
        }
        return String.valueOf(arr[0]);
    }

    private static boolean isPalindrome(char[] arr, int start, int end) {
        while (start < end) {
            if (arr[start++] != arr[end--]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 中心扩展法 (Expand Around Center) 是最实用、最值得掌握的 Longest Palindromic Substring（最长回文子串） 解法。
     */
    private static String longestPalindrome2(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) { //遍历整个 string
            int oddLen = expandAroundCenter(s, i, i); // 以i为中心向两边延申, 总长度为奇数
            int evenLen = expandAroundCenter(s, i, i + 1); // 以 i和i+1 为中心向两边延申, 总长度为偶数
            int len = Math.max(oddLen, evenLen); // 选择两者更大的那个

            if (len > end - start) { // 如果找到更长的回文, 则更新 start 和 end, 记录已知最长回文的起点终点.
                start = i - (len - 1) / 2; // 起点为以中点向左走 (长度 - 1) 的一半. start = 中心i - (len - 1) / 2
                end = i + len / 2; // 终点为以中点向右走长度的一半. end = 中心i + len / 2
            }
        }

        return s.substring(start, end + 1); // 当遍历完成, 输出记录的起点到终点的substring.
    }

    // 这个方程帮助确定最长回文的长度, 通过从中心向两边扩散, 再通过检查左边和右边是否相等来判断回文边界
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--; // 这里要想两边扩散, 所以左边要更左
                right++; // 右边要更右
            } else { // 如果左边一旦不等于右边, 就跳出循环.
                break;
            }
        }
        return right - left - 1;  // 返回左边到右边的距离
    }
}
