package src.Stack;

import java.util.EmptyStackException;
import java.util.Stack;

public class ImplementQueueUsingStacks_232 {
    /*Implement a first in first out (FIFO) queue using only two stacks.
    The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size,
and is empty operations are valid.
Depending on your language, the stack may not be supported natively.
You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.


Example 1:

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false


Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.


Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words,
performing n operations will take overall O(n) time even if one of those operations may take longer.*/

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        System.out.println(q.peek()); // 输出 1
        System.out.println(q.pop());  // 输出 1
        System.out.println(q.empty()); // 输出 false
        System.out.println(q.pop());  // 输出 2
        System.out.println(q.empty()); // 输出 true

        MyQueue q1 = new MyQueue();
        q1.push(10);
        q1.push(20);
        q1.push(30);
        System.out.println(q1.pop()); // 输出 10
        q1.push(40);
        System.out.println(q1.pop()); // 输出 20
        System.out.println(q1.pop()); // 输出 30
        System.out.println(q1.peek()); // 输出 40
        System.out.println(q1.pop()); // 输出 40
        System.out.println(q1.empty()); // 输出 true


    }


    /**
        一个经典的双Stack Queue实现.
        核心逻辑:
        做两个Stack, 一个只负责接入库的, 一个只负责接出库的.
        push时无条件压入入库栈.
        pop时, 如果出库栈为空则把全部入库栈的一一压入出库栈, 然后再从出库栈pop一个来return.
        peek时, 一样逻辑, 如果出库栈为空, 把全部入库栈的元素压入出库栈. peek一个出库栈的来return.
        isEmpty: 如果两个栈都是empty, 则queue为空.
        size: return 两栈size之和.
     */
    private static class MyQueue {

        private final Stack<Integer> inStack;
        private final Stack<Integer> outStack;

        private MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        private void push(int x) {
            inStack.push(x);
        }

        private int pop() {
            if (this.empty()) {
                throw new EmptyStackException();
            }
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }

        private int peek() {
            if (this.empty()) {
                throw new EmptyStackException();
            }
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.peek();
        }

        private boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

    }
}
