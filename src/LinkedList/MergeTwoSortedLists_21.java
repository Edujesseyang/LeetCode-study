package src.LinkedList;


public class MergeTwoSortedLists_21 {
    /*
    You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.



Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]


Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
    */


    private static class ListNode {
        int val = 0;
        ListNode next;

        private ListNode() {
        }

        private ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(5);

        ListNode result = mergeTwoLists(list1, list2);
        while (result != null) {
            System.out.print(result.val + ", ");
            result = result.next;
        }
    }

    /**
     * 一个经典的LinkedList的题, 主要考察几点, 第一点就是使用LinkedList是否熟悉, 第二就是会不会复用node.
     * 切记不可以重新new每一个node, 一定要复用所有的已经存在的node, 尽可能的少new东西.
     * @param list1 sorted
     * @param list2 sorted
     * @return sorted
     */
    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(); // new 一个dummyHead, 不在乎其数值,只为head
        ListNode current = dummyHead; // define 一个current临时node

        while (list1 != null && list2 != null) { // 如果两个list都没到头
            if (list1.val < list2.val) { // 判断那个node的值小
                current.next = list1; // 将临时node的next指向该node
                list1 = list1.next; // 跟新该list
            } else {
                current.next = list2; // 反之亦然
                list2 = list2.next;
            }
            current = current.next; // 更新临时node
        }

        current.next = list1 == null ? list2 : list1; // 最后看看那个list到头了, 将未到头的link到临时node

        return dummyHead.next; // 最后返回dummyHead的下一个node
    }
}
