package src.DoublePointer;

public class ReverseString_344 {
    /*
    Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.



Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]


Constraints:

1 <= s.length <= 105
s[i] is a printable ascii character.
    */

    public static void main(String[] args) {
        char[] test1 = {'h', 'e', 'l', 'l', 'o'};
        reverseString(test1);
        System.out.println(test1);  // [o, l, l, e, h]

        char[] test2 = {'J', 'e', 's', 's', 'e'};
        reverseString(test2);
        System.out.println(test2);  // [e, s, s, e, J]

        char[] test3 = {'a'};
        reverseString(test3);
        System.out.println(test3);  // [a]

        char[] test4 = {};
        reverseString(test4);
        System.out.println(test4);  // []

        char[] test5 = {'1', '2', '3', '4'};
        reverseString(test5);
        System.out.println(test5);  // [4, 3, 2, 1]
    }

    private static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }
}
