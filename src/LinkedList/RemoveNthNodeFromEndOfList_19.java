package src.LinkedList;

public class RemoveNthNodeFromEndOfList_19 {
    /*
    Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz


Follow up: Could you do this in one pass?
    */

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }

    // 先求size, 再找该node, 然后跳过.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode copyHead = head;
        int size = 0;
        while (copyHead != null) {
            copyHead = copyHead.next;
            size++;
        }

        ListNode prev = null;
        ListNode current = head;
        if (n < size) {
            int deleteIndex = size - n;
            for (int i = 0; i < deleteIndex; i++) {
                prev = current;
                current = current.next;
            }
            if (prev != null) {
                prev.next = current.next;
            }
        } else if (n == size) {
            head = head.next;
        }

        return head;
    }
}
