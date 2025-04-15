package src.LinkedList;

public class MiddleOfTheLinkedList_876 {
    /*
    Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.



Example 1:


Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
Example 2:


Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.


Constraints:

The number of nodes in the list is in the range [1, 100].
1 <= Node.val <= 100
    */

    private static class ListNode {
        private final int val;
        private ListNode next;

        private ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode test = new ListNode(0);
        ListNode current = test;
        for (int i = 1; i < 10; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        print(test);
        System.out.println("The mid node is: " + findMiddleNode(test).val + " Expected: 5\n");

        ListNode test2 = new ListNode(0);
        test2.next = new ListNode(1);
        print(test2);
        System.out.println("The mid node is: " + findMiddleNode(test2).val + " Expected: 1\n");
    }

    private static ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static void print(ListNode current) {
        while (current != null) {
            System.out.print(current.val + ", ");
            current = current.next;
        }
        System.out.println();
    }
}
