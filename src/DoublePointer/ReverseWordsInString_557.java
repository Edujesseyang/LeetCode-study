package src.DoublePointer;

public class ReverseWordsInString_557 {
    /*Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.



Example 1:

Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Example 2:

Input: s = "Mr Ding"
Output: "rM gniD"


Constraints:

1 <= s.length <= 5 * 104
s contains printable ASCII characters.
s does not contain any leading or trailing spaces.
There is at least one word in s.
All the words in s are separated by a single space.*/

    public static void main(String[] agrs) {
        String test = "I like running !";
        System.out.println("Before: " + test);
        System.out.println("After: " + reverseWords(test));
    }

    public static String reverseWords(String s) {
        // create charArr for in-place moving elements.
        char[] charArr = s.toCharArray();
        int len = charArr.length;

        for (int i = 0; i < len; i++) {
            // begin working space when hit white space.
            if (i == 0 || charArr[i - 1] == ' ') { // place i == 0 in the front to ensure charArr[i - 1] will not out of bond.
                int wordEnd_i = i;
                // update wordEnd, find the correct position.
                while (wordEnd_i + 1 < len && charArr[wordEnd_i + 1] != ' ') { // skip: last word and one char word
                    wordEnd_i++;
                }
                // i points the leading char in each word.
                reverseWord(charArr, i, wordEnd_i);
            }
        }

        return new String(charArr);
    }

    private static void reverseWord(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /*
     * 关键点:
     * 1. 善用helper function. 可以提高可读性.
     * 2. 运用while loop 来找到单词的末尾字母位置.
     * */

}
