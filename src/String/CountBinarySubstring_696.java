package src.String;

public class CountBinarySubstring_696 {
    /*
    Given a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's,
    and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.



Example 1:

Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
Example 2:

Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.


Constraints:

1 <= s.length <= 105
s[i] is either '0' or '1'.
    */

    public static void main(String[] args) {
        String[] tests = {"00100101010011100101", "001100110011", "0101010101", "AABBAABBAABBAAB", "0110011000110", "0", "10", "010"};
        int[] answers = {15, 10, 9, 13, 10, 0, 1, 2};
        for (int i = 0; i < tests.length; i++) {
            System.out.println("\nTest :" + tests[i]);
            System.out.println("Output: " + countSubstrings(tests[i]) + "  Expected: " + answers[i] + ((countSubstrings(tests[i]) == answers[i]) ? " Pass" : " Fail"));

        }
    }

    /**
     * 一个找关键变化点并统计前后状态的题, 这里关键变化点是每次从0变到1或从1变到0时.
     * 这时, 当改变的这一刻代表已经完成了一次从0到1又到0 的过程, 那么这时根据题的意思, 我们要统计这里面有多少符合要求的substring.
     * substring的数刚好相当于这里面1的数量和0的数量最小的那个数. 所以我们maintain两个counter, 一个监测从上次变化到此刻走过的元素数量,
     * 另一个记录上上次到上次走过的数量, 这两个刚好分别为0的数量和1的数量, 无论先后. 这时加总两者较小值到result, 然后更新preCount 为 currentCount.
     * 再次初始化currentCount = 1, 因为当前的pointer正在指向的数需要被统计上.
     * 最后, 循环结束时再把最后一轮个currentCount 和 上一个preCount 的较小的加总一下.
     */
    private static int countSubstrings(String s) {
        char[] arr = s.toCharArray();
        int preCount = 0, currentCount = 1, result = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                currentCount++;
            } else {
                result += Math.min(preCount, currentCount);
                preCount = currentCount;
                currentCount = 1;
            }
        }

        result += Math.min(preCount, currentCount);
        return result;
    }
}
