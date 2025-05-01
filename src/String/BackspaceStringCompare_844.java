package src.String;

import java.util.ArrayDeque;
import java.util.Deque;

public class BackspaceStringCompare_844 {
    /*
    Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.



Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".


Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.


Follow up: Can you solve it in O(n) time and O(1) space?
    */

    public static void main(String[] args) {
        System.out.println(approach1("ab#c", "ad#c"));  // true
        System.out.println(approach1("ab##", "c#d#"));  // true
        System.out.println(approach1("a#c", "b"));      // false
        System.out.println(approach1("a##c", "#a#c"));  // true
    }


    /**
     * 解法一, 修改两个string, 比较修改后的string, 多种修改的方法, stack, array, stringBuilder 都可以.
     * 逻辑上是一样的. 从前往后从后往前都行. 时间复杂度 O(n), 空间复杂的O(n)
     */
    private static boolean approach1(String s, String t) {
        if (s.equals(t)) return true;

        return makeStringUseStack(s).equals(makeStringUseArray(t)); // 使用两种方法都可以.
    }

    /**
     * 一个用stack的解法.
     */
    private static String makeStringUseStack(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());  // 从前插入，得到原顺序
        }
        return sb.toString();
    }

    /**
     * 利用一个arr来做新的string
     */
    private static String makeStringUseArray(String s) {
        int len = s.length();
        char[] arr = new char[len];
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '#') {
                if (j > 0) {
                    j--;
                }
            } else {
                arr[j] = s.charAt(i);
                j++;
            }
        }

        return new String(arr, 0, j);
    }

    private static boolean approach2(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            // 处理 s
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            // 处理 t
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            // 比较
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else if (i >= 0 || j >= 0) {
                return false;
            }

            i--;
            j--;
        }
        return true;
    }
}
