import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ValidParentheses_20 {
    /*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.


Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Example 5:

Input: s = "([)]"

Output: false



Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.*/

    public static void main(String[] args) {
        List<String> test = new ArrayList<>(Arrays.asList("()", "(){}[]", "{(})", "{[]}"));
        List<Boolean> answer = new ArrayList<>(Arrays.asList(true, true, false, true));

        for (int i = 0; i < test.size(); i++) {
            String s = test.get(i);
            System.out.println("\"" + s + "\" output: " + validParantheses(s) + " Expected: " + answer.get(i));
        }


    }

    public static boolean validParantheses(String s) {
        int len = s.length();
        if (len == 0) {
            return true;
        } else if (len % 2 != 0) {
            return false;
        }

        Stack<Character> charStack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                charStack.push(c);
            } else {
                if (charStack.empty()) {
                    return false;
                } else {
                    char top = charStack.pop();
                    if (c == ')' && top != '(') {
                        return false;
                    }
                    if (c == ']' && top != '[') {
                        return false;
                    }
                    if (c == '}' && top != '{') {
                        return false;
                    }
                }
            }
        }
        return charStack.isEmpty();
    }

    /*解法思路（用栈 Stack）：
    遇到左括号（(、{、[）就入栈；

    遇到右括号（)、}、]）时：

    如果栈为空，说明没有匹配的左括号，返回 false

    弹出栈顶，检查是否和当前右括号匹配

    最后检查栈是否为空，如果不为空说明有未关闭的括号，返回 false*/
}
