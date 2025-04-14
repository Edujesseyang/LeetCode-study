package src.LinkedList;


public class PalindromeLinkedList_234 {
    /*
    Given the head of a singly linked list, return true if it is a palindrome or false otherwise.



Example 1:


Input: head = [1,2,2,1]
Output: true
Example 2:


Input: head = [1,2]
Output: false


Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9


Follow up: Could you do it in O(n) time and O(1) space?
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
        ListNode<Integer> pointer = test;
        int count = 2;
        while (count <= 5) {
            pointer.next = new ListNode<>(count++);
            pointer = pointer.next;
        }
        while (count >= 1) {
            pointer.next = new ListNode<>(count--);
            pointer = pointer.next;
        }

        ListNode<Integer> test2 = new ListNode<>(1);
        pointer = test2;
        count = 3;
        while (count <= 5) {
            pointer.next = new ListNode<>(count++);
            pointer = pointer.next;
        }
        while (count > -1) {
            pointer.next = new ListNode<>(count--);
            pointer = pointer.next;
        }

        System.out.println("\n*** Test 1 ***");
        printList(test);
        System.out.println("This is palindrome :" + isPalindrome(test) + "\nRestore list:");
        printList(test);
        System.out.println("\n*** Test 2 ***");
        printList(test2);
        System.out.println("This is palindrome :" + isPalindrome(test2) + "\nRestore list:");
        printList(test2);
    }

    /**
     * 这是一个经典的单链表检查回文的题, 主要的逻辑是:
     * 1. 先找到链表的中点, 用快慢指针的方法, 快指针每次进2, 慢指针每次进1. 当快指针指向null时, 慢指针恰好是在中点.
     * 2. 这时慢指针指向后半部分的开头, 写一个helper method, 以慢指针为head, 对后半部分进行反转, 存到一个新的current指针, 作为后半部分的head
     * 3. 这时我们有反转slow之后得到后半部分的head, 和原本前半部分的head. 用双指针一起遍历, 如不同return false.
     * 4. 遍历完成, 证明前半部分和后半部分一样, return true
     * 5. 如有需要, 可将后半部分再反转一般以还原原本list.
     */
    private static <T> boolean isPalindrome(ListNode<T> head) {
        if (head == null || head.next == null) {
            return true;
        } // filter out empty list and one element list

        ListNode<T> fast = head;
        ListNode<T> slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        } // get the mid-point, it is slow


        ListNode<T> firstHalf = head;
        ListNode<T> secondHalf = reverse(slow);
        ListNode<T> midForRestore = secondHalf;

        boolean isPalindrome = true;
        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.val.equals(secondHalf.val)) {
                firstHalf = firstHalf.next;
                secondHalf = secondHalf.next;
            } else {
                isPalindrome = false;
                break;
            }
        }
        reverse(midForRestore); // (Optional) If you want to keep the original list
        return isPalindrome;
    }

    /**
     * 实现反转列表, input为一个node, output是一个反转之后的head
     * 主要逻辑是:
     *      1) 建一个prev pointer, 遍历list, 每次建一个临时指针存current的下一位,
     *      2) 将current 指向prev, 实现反转.
     *      3) 将prev 变成 current, 为下一轮准备
     *      4) 将current 变成临时存的下一位.
     */
    private static <T> ListNode<T> reverse(ListNode<T> current) {
        ListNode<T> prev = null;
        while (current != null) {
            ListNode<T> tempNext = current.next;
            current.next = prev;
            prev = current;
            current = tempNext;
        }
        return prev;
    }


    private static <T> void printList(ListNode<T> head) {
        ListNode<T> current = head;
        while (current != null) {
            System.out.print(current.val.toString() + ", ");
            current = current.next;
        }
        System.out.println();
    }
}
