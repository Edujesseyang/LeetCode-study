public class reverseString_541 {
    /*Given a string s and an integer k,
    reverse the first k characters for every 2k characters counting from the start of the string.
    If there are fewer than k characters left, reverse all of them.
    If there are less than 2k but greater than or equal to k characters,
    then reverse the first k characters and leave the other as original.



    Example 1:

    Input: s = "abcdefg", k = 2
    Output: "bacdfeg"

    Example 2:

    Input: s = "abcd", k = 2
    Output: "bacd"


    Constraints:

    1 <= s.length <= 104
    s consists of only lowercase English letters.
    1 <= k <= 104*/

    public static void main(String[] args) {
        String test = "abfhaegasdgahrw";
        System.out.println(reversString(test, 3));

    }

    public static String reversString(String s, int k) {
        char[] arr = s.toCharArray();
        int len = arr.length;

        for (int begin = 0; begin < len; begin += 2 * k) {
            int left = begin;
            int right = Math.min(begin + k - 1, len - 1); // 精妙之所在: 最末位, 或第后k位.

            while (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return new String(arr);
    }
    /* 双指针:
        大循环每k一跳, 小循环左右互换.
    *
    * */

}
