package src.LinkedList;

import java.util.Deque;
import java.util.LinkedList;

public class ReorderList_143 {
    /*
    You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.



Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]


Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
    */
    private class ListNode {
        private int val;
        private ListNode next;

        private ListNode() {

        }
    }

    public static void main(String[] args) {

    }

    private static void reorderList1(ListNode head) {
        // 剪枝
        if (head == null || head.next == null) {
            return;
        }

        // 1. 找到中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        } // 此时 slow 是中点

        // 2. 反转后半段
        ListNode prev = null, curr = slow.next;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        // 3. 切断尾部, 防止circle
        slow.next = null;

        // 4. 合并两个list
        ListNode first = head, second = prev; // prev 是后半的头, head 是前半段的头
        while (second != null) {
            ListNode temp1 = first.next;  // 预存两个list的下一位
            ListNode temp2 = second.next;

            first.next = second; // 前半段的curr指向后半段的curr
            second.next = temp1; // 后半段的curr指向前半段的curr下一位

            first = temp1; // 更新前半段curr
            second = temp2; // 更新后半段curr
        }
    }

    private static void reorderList2(ListNode head) {
        // 剪枝
        if (head == null || head.next == null) {
            return;
        }

        // 用双向列队存整个list
        Deque<ListNode> stack = new LinkedList<>();
        ListNode curr = head;
        while (curr != null) {
            stack.addLast(curr);
            curr = curr.next;
        }

        ListNode end = head; // 临时记录最后一位
        while (stack.size() > 1) { // 当stack有超过一个element时
            // 得出stack中的 头和尾
            ListNode first = stack.removeFirst();
            ListNode last = stack.removeLast();
            // 头指向尾
            first.next = last;
            // 尾指向stack中的第一位
            last.next = stack.peekFirst();
            end = last; // 更新当前最后一位
        }

        // 如果stack中还剩一个时, 则
        if (!stack.isEmpty()) {
            // 将它加到最后的尾部
            end.next = stack.removeFirst();
            end.next.next = null; // 并且将尾部后面切断
        }
    }
}
