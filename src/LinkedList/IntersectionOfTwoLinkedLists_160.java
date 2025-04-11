package src.LinkedList;


public class IntersectionOfTwoLinkedLists_160 {
    /*
    Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
    If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:


The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Custom Judge:

The inputs to the judge are given as follows (your program is not given these inputs):

intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
listA - The first linked list.
listB - The second linked list.
skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program.
If you correctly return the intersected node, then your solution will be accepted.



Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B
(2nd node in A and 3rd node in B) are different node references. In other words, they point
to two different locations in memory, while the nodes with value 8 in A and B
(3rd node in A and 4th node in B) point to the same location in memory.

Example 2:


Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4].
There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.

Example 3:


Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5].
Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.


Constraints:

The number of nodes of listA is in the m.
The number of nodes of listB is in the n.
1 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
intersectVal is 0 if listA and listB do not intersect.
intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.


Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
    */

    private static class ListNode {
        private int val;
        private ListNode next;

        private ListNode() {
        }

        private ListNode(int val) {
            this.val = val;
        }

        private ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        private int getVal() {
            return this.val;
        }

    }

    public static void main(String[] args) {
        ListNode test1 = new ListNode(1); // headA
        test1.next = new ListNode(2); // A2
        test1.next.next = new ListNode(3); //  A3 (begin intersection)

        ListNode test2 = new ListNode(5); // headB
        test2.next = new ListNode(6); //B2
        test2.next.next = test1.next.next; // B2 -> A3

        test1.next.next.next = new ListNode(7); // A4/B3
        test1.next.next.next.next = new ListNode(7); // A5/B4

        ListNode result = findIntersection(test1, test2);
        System.out.println("The start of intersection is " + result.getVal());
    }

    /**
     * 双指针解法：判断两个链表是否相交，并返回交点。
     * *
     * 思路：
     * 使用两个指针 pA 和 pB 分别从 headA 和 headB 开始遍历。
     * 当一个指针遍历到末尾时，跳转到另一个链表的头部继续遍历。
     * *
     * 设：
     *   - A 为链表 A 的独有长度
     *   - B 为链表 B 的独有长度
     *   - C 为它们相交的公共部分长度
     * *
     * 当 pA 和 pB 分别走完 A+C+B 和 B+C+A 之后，会在第一个交点相遇。
     * 若无交点，两者最终都为 null，跳出循环。
     * *
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(1)
     */
    private static ListNode findIntersection(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next; // 如果listA已经走完了, 指向B的头, 再走listB
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;
        // 或者return pB, 因为两者相交时 pA = pB, 不相交时也是 pA = pB = null,
        // 因为如果两条list都走完了, pA则一定会停在null, 恰好是当两者不相交时要求return的值.
    }
}
