package src.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckPrefixEachWord_1455 {
    /*Given a sentence that consists of some words separated by a single space, and a searchWord,
    check if searchWord is a prefix of any word in sentence.

Return the index of the word in sentence (1-indexed) where searchWord is a prefix of this word.
If searchWord is a prefix of more than one word, return the index of the first word (minimum index). If there is no such word return -1.

A prefix of a string s is any leading contiguous substring of s.



Example 1:

Input: sentence = "i love eating burger", searchWord = "burg"
Output: 4
Explanation: "burg" is prefix of "burger" which is the 4th word in the sentence.
Example 2:

Input: sentence = "this problem is an easy problem", searchWord = "pro"
Output: 2
Explanation: "pro" is prefix of "problem" which is the 2nd and the 6th word in the sentence, but we return 2 as it's the minimal index.
Example 3:

Input: sentence = "i am tired", searchWord = "you"
Output: -1
Explanation: "you" is not a prefix of any word in the sentence.


Constraints:

1 <= sentence.length <= 100
1 <= searchWord.length <= 10
sentence consists of lowercase English letters and spaces.
searchWord consists of lowercase English letters.*/

    public static void main(String[] args) {
        List<String> sentences = new ArrayList<>(Arrays.asList(
                "i love eating burger",
                "this problem is an easy problem",
                "i am tired",
                "hellohello hellohellohello",
                "aaaaa aaaaa aaaaa"));

        List<String> prefixes = new ArrayList<>(Arrays.asList(
                "burg",
                "pro",
                "by",
                "ell",
                "bbb"));

        int[] expecteds = {4, 2, -1, -1, -1};

        for (int i = 0; i < sentences.size(); i++) {
            System.out.println("Test " + (i + 1) + ": output: " + isPrefixOfWord(sentences.get(i), prefixes.get(i)) + " Expected: " + expecteds[i]);
        }

    }

    private static int isPrefixOfWord(String sentence, String prefix) {
        int sentenceLen = sentence.length();
        int prefixLen = prefix.length();

        int count = 1;
        for (int i = 0; i < sentenceLen; i++) {
            if (i == 0 || sentence.charAt(i - 1) == ' ') { // 精妙, 先判断i==0, 因为只有第一轮charAt会越界, 所以i==0 放前面渡过第一轮
                int sentence_i = i;
                int prefix_i = 0;
                while (sentence_i < sentenceLen && prefix_i < prefixLen && sentence.charAt(sentence_i) == prefix.charAt(prefix_i)) {
                    // 上面精妙, 用and先判断是否越界, 防止charAt时 out of bound
                    sentence_i++;
                    prefix_i++;
                }
                if (prefix_i == prefixLen) {
                    return count;
                }
                count++;
            }
        }
        return -1;
    }

    // 关键:
    //  只需从空格处开始做, 非空格不用管.
    //
}
