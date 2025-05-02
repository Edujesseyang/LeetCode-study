package src.Math;

class ConvertBinaryNumInLinkedListToInteger_1290 {
    /*
    Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1.
    The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.

The most significant bit is at the head of the linked list.



Example 1:


Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10
Example 2:

Input: head = [0]
Output: 0


Constraints:

The Linked List is not empty.
Number of nodes will not exceed 30.
Each node's value is either 0 or 1.
    */
    private static class LinkedNode {
        private int val;
        private LinkedNode next;

        private LinkedNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LinkedNode head = new LinkedNode(1);
        head.next = new LinkedNode(0);
        head.next.next = new LinkedNode(1);
        head.next.next.next = new LinkedNode(0);

        System.out.println("Test : '1010' : Output: " + convertToInt(head) + " Expected: 10");
    }

    /**
     * 我们可以用一个int[]去一个一个接每一位的数, 然后再loop这个int[], 每一位如果是1就 += 2 ** (len--). 但这种方法效率很低,
     * 我们要找一种方法从前左向右遍历. 所以以下的方式可以解决.
     * 从左往右每一位都把增加当前result的2倍, 然后再加上该位数字. 最后就可以得出正确结果.
     */
    private static int convertToInt(LinkedNode head) {
        int ans = 0;
        while (head != null) {
            ans += ans * 2 + head.val;
            head = head.next;
        }
        return ans;
    }
}
