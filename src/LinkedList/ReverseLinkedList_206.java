package src.LinkedList;

public class ReverseLinkedList_206 {
    /*
    Given the head of a singly linked list, reverse the list, and return the reversed list.



Example 1:


Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
Example 2:


Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []


Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000


Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
    */
    private static class ListNode {
        private int val;
        private ListNode next;

        private ListNode() {
        }

        private ListNode(int val) {
            this.val = val;
        }
    }


    public static void main(String[] args) {
        ListNode test = new ListNode(1);
        test.next = new ListNode(2);
        test.next.next = new ListNode(3);
        test.next.next.next = new ListNode(4);
        test.next.next.next.next = new ListNode(5);

        ListNode testResult = reverseList(test);
        while (testResult != null) {
            System.out.print(testResult.val + ", ");
            testResult = testResult.next;
        }
        System.out.println();

        ListNode testResultRecursively = reverseListRecursively(test);
        while (testResultRecursively != null) {
            System.out.print(testResultRecursively.val + ", ");
            testResultRecursively = testResultRecursively.next;
        }
        System.out.println();
    }

    /**
     * 按题目要求, 需要写出一个loop的版本一个递归的版本, 这个是loop的版本
     * 主要点是在建一个prev初始化为null, 每一轮建一个temp指向cur的下一个,
     * 然后让cur指向prev,做反转.
     * 然后让prev 指向cur 来做跟进
     * 最后向后移动cur.
     * 直到最后, prev会指向原本list的尾, 而cur会指向null, 所以return prev.
     */
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode currentNext = current.next;
            current.next = prev;
            prev = current;
            current = currentNext;
        }
        return prev;
    }

    /**
     * 这个是递归版本, 主要逻辑是先直接进递归到底, 然后回溯时每次:
     * 讲head的下一个指向head, 实现反转. 让head指向null做断链.
     * 然后回上层.
     */
    private static ListNode reverseListRecursively(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } // base case, head为空, 或head 后面为空

        ListNode newNode = reverseListRecursively(head.next); // 先进递归

        head.next.next = head; // 反转
        head.next = null; // 断链

        return newNode;
    }
}
