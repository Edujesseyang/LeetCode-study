import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTheIndexOfFirstOccurrenceInString_28 {
    /*Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.



Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
Example 2:

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.


Constraints:

1 <= haystack.length, needle.length <= 104
haystack and needle consist of only lowercase English characters.*/

    public static void main(String[] args) {
        List<String> haystacks = new ArrayList<>(Arrays.asList("sadbutsad", "aaa", "habbbo", "abc", "aaaaabb", "bgsetsdf"));
        List<String> needles = new ArrayList<>(Arrays.asList("sad", "a", "bbb", "c", "bb", "hesse"));
        int[] answers = {0, 0, 2, 2, 5, -1};

        for (int i = 0; i < answers.length; i++) {
            System.out.println("Test: " + (i + 1) + " output: " + findIndex(haystacks.get(i), needles.get(i)) + " expected: " + answers[i]);
        }

    }

    public static int findIndex(String hay, String nee) {
        int hayLen = hay.length();
        int neeLen = nee.length();
        if (neeLen > hayLen) {
            return -1;
        } else if (neeLen == 0) {
            return -1;
        }

        int h = 0;
        int n = 0;
        while (h < hayLen) {
            if (hay.charAt(h) == nee.charAt(n)) {
                h++;
                n++; // 先 更新指针, 再判断;
                if (n == neeLen) {
                    return h - n;
                }
            } else {
                h = h - n + 1;  //精妙之所在, 如果中间有不一样的, 整体退回去, 但是h+1. 如果是第一位就不同, h 还是会增加.
                n = 0;
            }
        }
        return -1;
    }

}
