package src.LinkedList;

public class RemoveDuplicatesFromLinkedList_83 {
    /*
    Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
    Return the linked list sorted as well.



Example 1:


Input: head = [1,1,2]
Output: [1,2]
Example 2:


Input: head = [1,1,2,3,3]
Output: [1,2,3]


Constraints:

The number of nodes in the list is in the range [0, 300].
-100 <= Node.val <= 100
The list is guaranteed to be sorted in ascending order.

    */
    private static class ListNode {
        int val;
        ListNode next;

        private ListNode() {
        }

        private ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode current = head;
        current.next = new ListNode(1);
        current = current.next;
        current.next = new ListNode(1);
        current = current.next;
        current.next = new ListNode(2);
        current = current.next;
        current.next = new ListNode(3);
        current = current.next;
        current.next = new ListNode(4);
        current = current.next;
        current.next = new ListNode(4);
        current = current.next;

        ListNode result = removeDuplicate(head);

        while (result != null) {
            System.out.print(result.val + ", ");
            result = result.next;
        }
    }

    /**
     * 基本的LinkList去重. 为了避免current.next.val和current.val对比导致的越界. 引入一个prev node. 用prev来和current比.
     * 重点在于判断是否一样时的跳跃和进位. 容易混乱.
     */
    private static ListNode removeDuplicate(ListNode head) {
        ListNode current = head.next; // 初始current在第二位
        ListNode prev = head; // prev在第一位
        while (current != null) { // 如果不到最后
            if (current.val == prev.val) { // 对比prev和current是否一样
                prev.next = current.next; // 如果一样, 跳过current, prev直接link到current的下一位
            } else {
                prev = prev.next; // 如果不一样, prev进位
            }
            current = current.next; // current每轮进位.
        }
        return head;
    }
}
