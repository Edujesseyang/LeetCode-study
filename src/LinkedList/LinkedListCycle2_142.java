package src.LinkedList;

public class LinkedListCycle2_142 {
    /*
    Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again
by continuously following the next pointer. Internally, pos is used to denote the index of the
node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle.
Note that pos is not passed as a parameter.

Do not modify the linked list.



Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:


Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:


Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.


Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.


Follow up: Can you solve it using O(1) (i.e. constant) memory?
    */

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

    }

    public static void main(String[] args) {
        // 测试用例 1: 无环
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        System.out.println("Case 1: No cycle \nOutput: " + detectCycle(head1) + " Expected: null\n"); // 输出 null

        // 测试用例 2: 有环，环从节点 2 开始
        ListNode head2 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head2.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // 形成环
        System.out.println("Case 2: Cycle at 2 \nOutput: " + detectCycle(head2).val + " Expected: 2\n"); // 输出 2

        // 测试用例 3: 整个链表就是一个环（自己指向自己）
        ListNode head3 = new ListNode(1);
        head3.next = head3;
        System.out.println("Case 3: Cycle at head\nOutput: " + detectCycle(head3).val + " Expected: 1\n"); // 输出 1

        // 测试用例 4: 两个节点，有环
        ListNode head4 = new ListNode(1);
        ListNode node5 = new ListNode(2);
        head4.next = node5;
        node5.next = head4; // 形成环
        System.out.println("Case 4: Double cycle\nOutput: " + detectCycle(head4).val + " Expected: 1\n"); // 输出 1

        // 测试用例 5: 两个节点，无环
        ListNode head5 = new ListNode(1);
        head5.next = new ListNode(2);
        System.out.println("Case 5: Two nodes, No cycle\nOutput: " + detectCycle(head5) + " Expected: null\n"); // 输出 null
    }

    /**
     * Floyd 环判的升级版:
     * 还是快针走两步慢针走一步, 不同的是这题要找到入口. 有一个问题, 就是快针和慢针不总在入口处相遇, 可能在环里任何一个位置相遇.
     * 那么重点在于当他们两个相遇的时候, 如何要找到入口. 这里引入了一个数学推理法:
     * 变量设定:
     *      L：从链表头到环入口的距离。
     *      C：环的长度（环内节点数）。
     *      x：从环入口到快慢指针第一次相遇点的距离（沿着环走）。
     *      n：快指针在环内多转了几圈。
     * 相遇时的路径关系：
     *      慢指针走了：L + x 步。
     *      快指针走了：L + x + nC 步（环内多转了 n 圈）。
     * 因为 fast 是 slow 的两倍速度：
     *      快指针走的步数 = 2 * 慢指针走的步数
     * 所以：
     *      L + x + nC = 2(L + x)
     * 代数推导：
     *      化简公式：
     *      L + x + nC = 2L + 2x
     *      移项：
     *      L + x + nC = 2L + 2x
     *      nC = L + x
     *      关键等式：
     *      L = nC − x
     * nC 是环的整数倍，表示快指针比慢指针多走了整圈。
     * 所以：L = (整圈 - x)，说明：
     * 从相遇点再走 L 步，恰好走到环的入口。
     * 这时我们把任意一个指针重新指向head, 然后再一人一步的跑一遍即可得到环的入口.
     */
    private static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head; // 关键1: 一定从同一起点出发, 不然无法保证L的长度等于C中相遇点到入口的长度
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next; // 关键2: 一定想移动, 因为初发点相同, 而且我们下面要判断两个pointer是否相同. 所以要先做移动.
            slow = slow.next;

            if (fast == slow) { // 如果相遇了, 要判断入口.
                slow = head; // 任意一点会头
                while (fast != slow) { // 各走一步直到相遇.
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow; // 相遇处既是入口.
            }
        }
        return null;
    }
}
