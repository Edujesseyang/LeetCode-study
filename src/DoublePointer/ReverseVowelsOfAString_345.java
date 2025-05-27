package src.DoublePointer;

public class ReverseVowelsOfAString_345 {
    /*
    Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.



Example 1:

Input: s = "IceCreAm"

Output: "AceCreIm"

Explanation:

The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

Example 2:

Input: s = "leetcode"

Output: "leotcede"



Constraints:

1 <= s.length <= 3 * 105
s consist of printable ASCII characters.
    */
    public static void main(String[] args) {
        String test = "LeEtcodE";
        System.out.println("Test : " + test + " Output : " + reverseVowels(test));

    }


    // 用双指针左右交换的方法来做, 一个swap, 一个checker, 左右各自快进至下一个元音, 然后交换, 再左右各走一步, 进入下个循环.
    private static String reverseVowels(String s) {
        char[] sArr = s.toCharArray();
        int left = 0, right = sArr.length - 1;

        while (left < right) {
            while (left < right && !isVowel(sArr[left])) {
                left++;
            }
            while ((left < right && !isVowel(sArr[right]))) {
                right--;
            }
            swap(sArr, left++, right--);
        }
        return new String(sArr);
    }

    private static boolean isVowel(char c) {
        switch (c) {
            case 'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U':
                return true;
            default:
                return false;
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
