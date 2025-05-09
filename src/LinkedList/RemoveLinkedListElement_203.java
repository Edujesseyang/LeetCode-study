package src.LinkedList;

public class RemoveLinkedListElement_203 {

    /*
    Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.



Example 1:


Input: head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
Example 2:

Input: head = [], val = 1
Output: []
Example 3:

Input: head = [7,7,7,7], val = 7
Output: []


Constraints:

The number of nodes in the list is in the range [0, 104].
1 <= Node.val <= 50
0 <= val <= 50
     */
    private static class ListNode<T> {

        private final T val;
        private ListNode<T> next;

        private ListNode(T val) {
            this.val = val;
        }

    }

    public static void main(String[] args) {
        ListNode<Integer> test = new ListNode<>(1);
        ListNode<Integer> current = test;
        current.next = new ListNode<>(1);
        current = current.next;
        current.next = new ListNode<>(1);
        current = current.next;
        for (int i = 0; i < 6; i++) {
            current.next = new ListNode<>((int) (Math.random() * 10));
            current = current.next;
        }
        current.next = new ListNode<>(1);
        current.next.next = new ListNode<>(1);

        ListNode<Integer> printPointer = test;
        while (printPointer != null) {
            System.out.print(printPointer.val + ", ");
            printPointer = printPointer.next;
        }
        System.out.println("\nAfter remove:");
        ListNode<Integer> afterRemove = removeElement(test, 1);
        while (afterRemove != null) {
            System.out.print(afterRemove.val + ", ");
            afterRemove = afterRemove.next;
        }
        System.out.println();
    }

    /**
     * 一个linkedList经典的整体删除, 这个解法时间复杂度是O(n), 空间是O(1) 这里的难点是在如果处理删除这个逻辑, 有很多方法,
     * 比如建立一个prev, 让current在prev的前面找相同的, 找到了就把prev link 到current的下一个.
     * 最后在检查一个head是不是相同, 如果是的话head进一位. 这里的做法是先在head的前面加上一个dummy, 用dummy作为头,
     * 用current的下一位来比对, 这时就能覆盖到head了. 最后return dummy的下一位.
     */
    private static ListNode<Integer> removeElement(ListNode<Integer> head, int val) {
        ListNode<Integer> dummy = new ListNode<>(-1);
        dummy.next = head;
        ListNode<Integer> current = dummy;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next; // 如果相同, 跳一位
            } else {
                current = current.next; // 如果不同, 进一位
            }
        }
        return dummy.next;
    }
}
