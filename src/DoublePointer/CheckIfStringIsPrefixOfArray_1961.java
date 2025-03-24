package src.DoublePointer;

public class CheckIfStringIsPrefixOfArray_1961 {
    /*Given a string s and an array of strings words, determine whether s is a prefix string of words.

A string s is a prefix string of words if s can be made by concatenating the first k strings in words for some positive k no larger than words.length.

Return true if s is a prefix string of words, or false otherwise.



Example 1:

Input: s = "iloveleetcode", words = ["i","love","leetcode","apples"]
Output: true
Explanation:
s can be made by concatenating "i", "love", and "leetcode" together.
Example 2:

Input: s = "iloveleetcode", words = ["apples","i","love","leetcode"]
Output: false
Explanation:
It is impossible to make s using a prefix of arr.


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
1 <= s.length <= 1000
words[i] and s consist of only lowercase English letters.*/

    public static void main(String[] args) {
        String test1 = "iloveleetcode";
        String[] words1 = {"i", "love", "leetcode", "apples"};
        String[] words2 = {"apples", "i", "love", "leetcode"};
        System.out.println("Test 1: output = " + isPrefixString(test1, words1) + " Expected = ture");
        System.out.println("Test 2: output = " + isPrefixString(test1, words2) + " Expected = false");


    }

    private static boolean isPrefixString(String s, String[] words) {
        // define a pointer check char in the input string.
        int stringIndex = 0;
        int stringLen = s.length();

        // iterate all elements in words
        for (String word : words) {
            // get each element's len
            int wordLen = word.length();
            // iterate all char in each element
            for (int j = 0; j < wordLen; j++) {
                // if the current element passes the last char of the input string
                if (stringIndex + wordLen > stringLen) {
                    return false;
                }

                // if their char dose doesn't match
                if (word.charAt(j) != s.charAt(stringIndex + j)) { // 重点! j 相当于string的临时指针
                    return false;
                }
            }
            // update stringIndex
            stringIndex += wordLen;

            // if the stringIndex reaches the last position of the input string
            if (stringIndex == stringLen) {
                return true;
            }
        }
        return false;
    }

    /*关键点:
    1. 要loop array 而不是 input string
    2. 在比对每个element和input string的char时, 可以利用loop element时的指针j, 当作input string 的临时指针
    3. 每完成一个word的比对时, 更性string 的指针, 直接加该element 的length
    4. 判断完成时, 如果string 指针到达最末尾可视为完成, 但细节在于, 我们没有每次把指针+1, 而是直接加上一个element长度,
        所以这里的最末尾指针应该是 等于string length. 因为 stringLen 代表的是字符串的长度, 而不是最后一位的索引.
    * */

}
