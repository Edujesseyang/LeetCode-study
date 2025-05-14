package src.LinkedList;

public class AddTwoNumbers_2 {
    /*
    You are given two non-empty linked lists representing two non-negative integers.
    The digits are stored in reverse order, and each of their nodes contains a single digit.
    Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.



Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]


Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
    */
    private static class LinkNode {
        private int val;
        private LinkNode next;

        private LinkNode() {
        }

        private LinkNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LinkNode testList1 = new LinkNode(2);
        testList1.next = new LinkNode(4);
        testList1.next.next = new LinkNode(3);

        LinkNode testList2 = new LinkNode(5);
        testList2.next = new LinkNode(6);
        testList2.next.next = new LinkNode(4);

        LinkNode result = addTwoNum(testList1, testList2);

        while (result != null) {
            System.out.print(result.val + ",");
            result = result.next;
        }
    }

    private static LinkNode addTwoNum(LinkNode l1, LinkNode l2) {
        // create return answer
        LinkNode dummy = new LinkNode();
        LinkNode current = dummy;

        // create two pointers for both list
        LinkNode c1 = l1, c2 = l2;

        // initial carry value
        int carry = 0;

        // main logic
        while (c1 != null || c2 != null || carry != 0) {
            int num1 = c1 == null ? 0 : c1.val;
            int num2 = c2 == null ? 0 : c2.val;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            current.next = new LinkNode(sum % 10);
            current = current.next;

            if (c1 != null) {
                c1 = c1.next;
            }
            if (c2 != null) {
                c2 = c2.next;
            }
        }

        return dummy.next;
    }
}
