package src.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReversePrefixWord_2000 {
    /*Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.

For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".
Return the resulting string.



Example 1:

Input: word = "abcdefd", ch = "d"
Output: "dcbaefd"
Explanation: The first occurrence of "d" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
Example 2:

Input: word = "xyxzxe", ch = "z"
Output: "zxyxxe"
Explanation: The first and only occurrence of "z" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
Example 3:

Input: word = "abcd", ch = "z"
Output: "abcd"
Explanation: "z" does not exist in word.
You should not do any reverse operation, the resulting string is "abcd".


Constraints:

1 <= word.length <= 250
word consists of lowercase English letters.
ch is a lowercase English letter.*/

    public static void main(String[] args) {
        List<String> tests = new ArrayList<>(Arrays.asList("resulting", "Constraints", "explanations"));
        char[] chars = {'l', 'r', 's'};
        for (int i = 0; i < tests.size(); i++) {
            System.out.println("Test " + (i + 1) + ": \"" + tests.get(i) + "\" char: " + chars[i]);
            System.out.println("output: " + reversePrefix(tests.get(i), chars[i]));
        }
    }

    public static String reversePrefix(String word, char c) {
        // create a char[] for moving chars in-place
        char[] wordArr = word.toCharArray();

        // define initial pointers
        int wordBegin = 0;
        int targetPosition = 0;

        // find the right position for right
        while (wordArr[targetPosition] != c) {
            // if the last word does not match
            if (targetPosition + 1 == wordArr.length) {
                // means there is not such the char found.
                // right go back to 0.
                targetPosition = 0;
                break;
            }
            targetPosition++;
        }

        if (wordBegin != targetPosition) { // if the char is not the first char of the word
            // reverse
            reverse(wordArr, wordBegin, targetPosition);
        }

        return new String(wordArr);
    }

    private static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
