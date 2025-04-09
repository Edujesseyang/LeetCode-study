package src.LinkedList;

public class LinkedListCycle_141 {
    /*
    Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again
by continuously following the next pointer.
Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.



Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
Example 2:


Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
Example 3:


Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.


Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.


Follow up: Can you solve it using O(1) (i.e. constant) memory?
    */

    private static class LinkNode {
        int val = 0;
        LinkNode next;

        private LinkNode() {

        }

        private LinkNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LinkNode testHead = new LinkNode(1);
        LinkNode testCurrent = testHead;
        testCurrent.next = new LinkNode(2);
        testCurrent = testCurrent.next;
        testCurrent.next = new LinkNode(3);
        testCurrent = testCurrent.next;
        LinkNode cycleNode = testCurrent;
        testCurrent.next = new LinkNode(4);
        testCurrent = testCurrent.next;
        testCurrent.next = new LinkNode(5);
        testCurrent = testCurrent.next;
        testCurrent.next = new LinkNode(6);
        testCurrent = testCurrent.next;
        testCurrent.next = cycleNode;

        printList(testHead);

        System.out.println("\nIs this a cycle: " + isCycle(testHead));
    }

    /**
     * 这是个经典的linkedList查是否是环的题， 最优的解法是， 设快慢针， 慢的在第一位， 快的在第二位，
     * 然后进入循环，如果快针能够到达list的尾， 意味着没有环， 每次将快针移动2位，而慢针移动一位。 如果是环，他们终会相遇。
     * @param head
     * @return
     */
    private static boolean isCycle(LinkNode head) {
        // if the head is null, only the head is the only node
        if (head == null || head.next == null) {
            return false; // In this case , it not a cycle
        }

        // defne two pointer, initiate them next to each other
        LinkNode slow = head;
        LinkNode fast = head.next;

        while (slow != fast) { // if they are not same address, do...
            if (fast == null || fast.next == null) { // check if fast reaches the end.
                return false; // it means that this list is not a cycle list
            }
            fast = fast.next.next; // fast moves two steps
            slow = slow.next; // slow moves one step
        }

        return true; // return true, if the loop finished, it means that fast meets slow. Indicate that it is a cycle.
    }


    private static void printList(LinkNode head) {
        LinkNode current = head;
        for (int i = 0; i < 20; i++) {
            System.out.print(current.val + ", ");
            current = current.next;
        }
    }
}
