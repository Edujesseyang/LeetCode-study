package src.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BaseballGame_682 {
    /*
You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.

You are given a list of strings operations, where operations[i] is the ith operation you must apply to the record and is one of the following:

An integer x.
Record a new score of x.
'+'.
Record a new score that is the sum of the previous two scores.
'D'.
Record a new score that is the double of the previous score.
'C'.
Invalidate the previous score, removing it from the record.
Return the sum of all the scores on the record after applying all the operations.

The test cases are generated such that the answer and all intermediate calculations fit in a 32-bit integer and that all operations are valid.



Example 1:

Input: ops = ["5","2","C","D","+"]
Output: 30
Explanation:
"5" - Add 5 to the record, record is now [5].
"2" - Add 2 to the record, record is now [5, 2].
"C" - Invalidate and remove the previous score, record is now [5].
"D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
"+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
Example 2:

Input: ops = ["5","-2","4","C","D","9","+","+"]
Output: 27
Explanation:
"5" - Add 5 to the record, record is now [5].
"-2" - Add -2 to the record, record is now [5, -2].
"4" - Add 4 to the record, record is now [5, -2, 4].
"C" - Invalidate and remove the previous score, record is now [5, -2].
"D" - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
"9" - Add 9 to the record, record is now [5, -2, -4, 9].
"+" - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
"+" - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
Example 3:

Input: ops = ["1","C"]
Output: 0
Explanation:
"1" - Add 1 to the record, record is now [1].
"C" - Invalidate and remove the previous score, record is now [].
Since the record is empty, the total sum is 0.


Constraints:

1 <= operations.length <= 1000
operations[i] is "C", "D", "+", or a string representing an integer in the range [-3 * 104, 3 * 104].
For operation "+", there will always be at least two previous scores on the record.
For operations "C" and "D", there will always be at least one previous score on the record.
*/

    public static void main(String[] args) {
        String[] ops = {"5", "2", "C", "D", "+"};
        String[] ops2 = {"5", "-2", "4", "C", "D", "9", "+", "+"};

        System.out.println("Test 1: Output : " + countScore(ops) + " Expected: 30");
        System.out.println("Test 2: Output : " + countScore(ops2) + " Expected: 27");
    }

    /**
     * 这是一个经典的[[Stack]]应用, 运用一个[[Deque]]来维护整个计分的过程.
     * 1. 每当遇到`C`时就将栈顶元素丢掉.
     * 2. 每当遇到`D`时就加入当前栈顶元素`*2`.
     * 3. 每当遇到`+`时就将栈顶元素先提出来, 然后再`peek`一下拿到下一个元素, 之后将最先提出的原栈顶元素`push`回去, 最后再用之前提出来的栈顶和第二个元素相加并`push`进栈.
     * 4. 遇到任何其他的情况则, 将其`parse`成`int`之后再压入栈顶.
     * 最后遍历一下栈,累加得到总数, 并return.
     */
    private static int countScore(String[] operations) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : operations) {
            switch (s) {
                case "C" -> stack.pop();
                case "D" -> {
                    if (!stack.isEmpty()) {
                        stack.push(stack.peek() * 2);
                    }
                }
                case "+" -> {
                    int pre1 = stack.pop();
                    int pre2 = 0;
                    if (!stack.isEmpty()) {
                        pre2 = stack.peek();
                    }
                    stack.push(pre1);
                    stack.push(pre1 + pre2);
                }
                default -> stack.push(Integer.parseInt(s));
            }
        }

        int sum = 0;
        for (int i : stack) {
            sum += i;
        }
        return sum;
    }
}
