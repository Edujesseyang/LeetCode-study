package src.Stack;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueue_225 {
    /*
    Implement a last-in-first-out (LIFO) stack using only two queues.
    The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back,
peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively.
You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.


Example 1:

Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False


Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.


Follow-up: Can you implement the stack using only one queue?
    */

    /**
     * 这个是一个慢进快出的版本, 这里利用了queue的性质, 在把queue poll出来的element从下offer给他自己size-1次后, 第一个加入的element就变成了最后一个位置.
     * 只有在add时重新加一圈, pop时直接poll出来更新后queue里的第一个element, 刚好是到处第二个加入的element, 实现了pop() 的LIFO的特性.
     * push: O(n), poll/peek: O(1)
     */
    private static class MyStack2<T> {
        private final Queue<T> queue;

        private MyStack2() {
            this.queue = new LinkedList<>();
        }

        private void push(T val) {
            queue.offer(val);
            int count = queue.size() - 1;
            while (count-- > 0) {
                queue.offer(queue.poll()); // 循环一圈
            }
        }

        private T pop() {
            return queue.poll();
        }

        private T top() {
            return queue.peek();
        }

        private boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    /**
     * 一个用queue实现stack的题, 利用把queue poll出来的element重新offer给原queue的方法, 只要offer size-1 次就是得到下一个element是最后加入的.
     * 这个实现是一个快入慢出的实现, push需要O(1), 而pop需要O(n).
     */
    private static class MyStack<T> {
        private final Queue<T> queue;

        private MyStack() {
            this.queue = new LinkedList<>();
        }

        private void push(T val) {
            queue.offer(val);
        }

        private T pop() {
            int count = queue.size() - 1;
            while (count-- > 0) {
                queue.offer(queue.poll()); // 循环一圈,
            }
            return queue.poll();
        }

        private T top() {
            int count = queue.size() - 1;
            while (count-- > 0) {
                queue.offer(queue.poll()); // 循环一圈
            }
            T returnVal = queue.poll();
            queue.offer(returnVal);
            return returnVal;
        }

        private boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {
        System.out.println("******** Test 1 ********");
        MyStack<Integer> test = new MyStack<>();
        System.out.println("Add 1");
        test.push(1);
        System.out.println("Add 2");
        test.push(2);
        System.out.println("Add 3");
        test.push(3);
        System.out.println("Top: " + test.top());
        System.out.println("Pop: " + test.pop());
        System.out.println("Top: " + test.top());
        System.out.println("Pop: " + test.pop());
        System.out.println("Top: " + test.top());
        System.out.println("Pop: " + test.pop());
        System.out.println("Is empty: " + test.isEmpty());

        System.out.println("******** Test 2 ********");
        MyStack2<Integer> test2 = new MyStack2<>();
        System.out.println("Add 1");
        test2.push(1);
        System.out.println("Add 2");
        test2.push(2);
        System.out.println("Add 3");
        test2.push(3);
        System.out.println("Top: " + test2.top());
        System.out.println("Pop: " + test2.pop());
        System.out.println("Top: " + test2.top());
        System.out.println("Pop: " + test2.pop());
        System.out.println("Top: " + test2.top());
        System.out.println("Pop: " + test2.pop());
        System.out.println("Is empty: " + test2.isEmpty());

    }
}
