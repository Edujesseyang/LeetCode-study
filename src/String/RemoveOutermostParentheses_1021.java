package src.String;

public class RemoveOutermostParentheses_1021 {
    /*
    A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid parentheses strings,
    and + represents string concatenation.

For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
A valid parentheses string s is primitive if it is nonempty, and there does not exist a way to split it into s = A + B,
with A and B nonempty valid parentheses strings.

Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk,
where Pi are primitive valid parentheses strings.

Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.



Example 1:

Input: s = "(()())(())"
Output: "()()()"
Explanation:
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing the outer parentheses of each part, this is "()()" + "()" = "()()()".
Example 2:

Input: s = "(()())(())(()(()))"
Output: "()()()()(())"
Explanation:
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing the outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
Example 3:

Input: s = "()()"
Output: ""
Explanation:
The input string is "()()", with primitive decomposition "()" + "()".
After removing the outer parentheses of each part, this is "" + "" = "".


Constraints:

1 <= s.length <= 105
s[i] is either '(' or ')'.
s is a valid parentheses string.
    */

    public static void main(String[] args) {
        String[] tests = {"(()())(())", "(()())(())(()(()))", "()()"};
        for (String s : tests) {
            System.out.println("Test: \"" + s + "\" Output: \"" + removeOuterParentheses(s) + "\"");
        }
    }

    /**
     * 思路, 使用计数法来判断是否得到了primitive decomposition. 每次遇到 '(' 时增加计数, 遇到 ')' 时减小计数.
     * 当计数为零时则得到一个完整的primitive decomposition. 添加时注意, 当计数为零时的当前 '(' 不用添加, 因为时最外层的开头.
     * 当计数为零时的当前 ')' 也不用添加, 因为时最外层的结尾. 用更新count和判断添加的顺序来控制添加逻辑.
     */
    private static String removeOuterParentheses(String s) {
        StringBuilder ans = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (count != 0) { // 先判断是否需要添加
                    ans.append(c);
                }
                count++; // 在增加count
            } else {
                count--; // 先减少count
                if (count != 0) { // 再判断是否需要添加
                    ans.append(c);
                }
            }
        }
        return ans.toString();
    }
}
