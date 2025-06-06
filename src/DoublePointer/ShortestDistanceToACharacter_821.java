package src.DoublePointer;

public class ShortestDistanceToACharacter_821 {
    /*
    Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the distance from index i to the closest occurrence of character c in s.

    The distance between two indices i and j is abs(i - j), where abs is the absolute value function.



    Example 1:

    Input: s = "loveleetcode", c = "e"
    Output: [3,2,1,0,1,0,0,1,2,2,1,0]
    Explanation: The character 'e' appears at indices 3, 5, 6, and 11 (0-indexed).
    The closest occurrence of 'e' for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
    The closest occurrence of 'e' for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
    For index 4, there is a tie between the 'e' at index 3 and the 'e' at index 5, but the distance is still the same: abs(4 - 3) == abs(4 - 5) = 1.
    The closest occurrence of 'e' for index 8 is at index 6, so the distance is abs(8 - 6) = 2.
    Example 2:

    Input: s = "aaab", c = "b"
    Output: [3,2,1,0]


    Constraints:

            1 <= s.length <= 104
    s[i] and c are lowercase English letters.
    It is guaranteed that c occurs at least once in s.
    */

    public static void main(String[] args) {
        test("loveleetcode", 'e');   // [3,2,1,0,1,0,0,1,2,2,1,0]
        test("aaab", 'b');           // [3,2,1,0]
        test("eabcd", 'e');          // [0,1,2,3,4]
        test("abcdz", 'z');          // [4,3,2,1,0]
        test("cccccc", 'c');         // [0,0,0,0,0,0]
        test("abcde", 'c');          // [2,1,0,1,2]
    }

    public static void test(String s, char c) {
        int[] result = shortestToChar(s, c);
        System.out.print("Input: \"" + s + "\", '" + c + "' => Output: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    /**
     * 解法思路（Two Pass）
     * 第一次从左往右扫：
     * 用一个变量 prev 记录上一次出现字符 c 的位置。
     * 初始化为 Integer.MIN_VALUE / 2 避免溢出。
     * 对每个字符 s[i]，计算到 prev 的距离 i - prev。
     * 第二次从右往左扫：
     * 用一个变量 next 记录下一个出现字符 c 的位置。
     * 初始化为 Integer.MAX_VALUE / 2。
     * 对每个字符 s[i]，更新为 min(answer[i], next - i)。
     */
    private static int[] shortestToChar(String s, char c) {
        int len = s.length();
        int[] ans = new int[len];

        int prev = Integer.MIN_VALUE / 2;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == c) {
                prev = i;
            }
            ans[i] = i - prev;
        }

        int next = Integer.MAX_VALUE / 2;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {

                next = i;
            }
            ans[i] = Math.min(ans[i], next - i);
        }

        return ans;
    }
}
