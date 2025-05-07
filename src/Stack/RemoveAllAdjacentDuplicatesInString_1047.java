package src.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveAllAdjacentDuplicatesInString_1047 {
    /*
    You are given a string s consisting of lowercase English letters.
    A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.



Example 1:

Input: s = "abbaca"
Output: "ca"
Explanation:
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.
The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
Example 2:

Input: s = "azxxzy"
Output: "ay"


Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
    */

    public static void main(String[] args) {
        String[] tests = {"azxxzy", "abbaca"};
        String[] answers = {"ay", "ca"};

        // Test approach 1:
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test: " + (i + 1) + " " + tests[i] + " Output: " + removeDuplicate(tests[i]) + " Expected: " + answers[i]);
        }

        // Test approach 2:
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Test: " + (i + 1) + " " + tests[i] + " Output: " + removeDuplicate2(tests[i]) + " Expected: " + answers[i]);
        }
    }

    /**
     * 用一个stack来收集可以留下来的字符, 利用FIFO和pop()的特性来实现.
     */
    private static String removeDuplicate(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!stack.isEmpty() && stack.getFirst() == s.charAt(i)) {
                stack.removeFirst();
            } else {
                stack.addFirst(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }

        return sb.toString();
    }

    private static String removeDuplicate2(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int len = sb.length();
            if (len > 0 && sb.charAt(len - 1) == c) {
                sb.deleteCharAt(len - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


}
