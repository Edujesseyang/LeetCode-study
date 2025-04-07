package src.String;

public class StringToInteger_8 {
    /*
    Implement the myAtoI(string s) function, which converts a string to a 32-bit signed integer.

The algorithm for myAtoI(string s) is as follows:

Whitespace: Ignore any leading whitespace (" ").
Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached.
If no digits were read, then the result is 0.
Rounding: If the integer is out of the 32-bit signed integer range [-2^31, 2^31-1], then round the integer to remain in the range.
Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
Return the integer as the final result.



Example 1:

Input: s = "42"

Output: 42

Explanation:

The underlined characters are what is read in, and the caret is the current reader position.
Step 1: "42" (no characters read because there is no leading whitespace)
         ^
Step 2: "42" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "42" ("42" is read in)
           ^
Example 2:

Input: s = " -042"

Output: -42

Explanation:

Step 1: "   -042" (leading whitespace is read and ignored)
            ^
Step 2: "   -042" ('-' is read, so the result should be negative)
             ^
Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
               ^
Example 3:

Input: s = "1337c0d3"

Output: 1337

Explanation:

Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
         ^
Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
             ^
Example 4:

Input: s = "0-1"

Output: 0

Explanation:

Step 1: "0-1" (no characters read because there is no leading whitespace)
         ^
Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
          ^
Example 5:

Input: s = "words and 987"

Output: 0

Explanation:

Reading stops at the first non-digit character 'w'.



Constraints:

0 <= s.length <= 200
s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
    */

    public static void main(String[] args) {
        String[] tests = {"42", " -042", "1337c0d3", "words and 987", "-99999999999999999999"};
        for (String s : tests) {
            System.out.println(s + " to int = " + aToi(s));
        }
    }

    /**
     * 这一题主要考察的解耦能力和边界意识, 此题的要求可以被分为三个部分,
     * 1. 首先是前面的空白区, 这里面只需要跳过空白即可, 不需要判断其他的.
     * 2. 数字前面的符合部分, 这里只需要抓取第一个遇到的符合, 如果又多符合则是无效输入, 在第3部分会被解决.
     * 3. 处理数字部分, 将每一个char减去48就等于对应的int, 因为'0'是在ASCII的48的位置.
     *    接着判断目前的result成10再加7以后会不会溢出int, 如果会直接return对应的MAX/MIN既可.
     *    然后如果result安全, 则乘10加当前抓取的数字.
     * 最后就是判断是否是负数, return相应值即可.
     * 另: 此类需要很多复杂判断的题型一定要注意解耦, 没有必要每一个element都做的判断完全可以分区分别判断, 不用一个for循环到底, 中间各种if else.
     * 就像一台流水线一样, 先把头部分需要做掉. 然后pinter别重置, 接着直接处理中间部分, 一个block一个block的处理. 可以大幅缩小需要使用到的逻辑判断.
     */
    private static int aToi(String s) {
        // filter out null and empty case
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int i = 0, n = s.length(); // define pointer and length
        boolean isNeg = false;
        // part 1: take care those leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++; // skip all leading white spaces
        }

        // part 2: take care signs
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) { // only check once, multiple sign will be taken care in the main loop
            isNeg = s.charAt(i) == '-'; // check if there is a negative sign.
            i++;
        }

        // part 3: take care nums
        int result = 0;
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int digit = s.charAt(i) - '0'; // ASCII: '0' = 48

            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) { // the last digit of MAX_INT is 7
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                // Return here, instead of carrying the result as MAX_INT or MIN_INT to the final return, and then return (-result) over there.
                // Because -MAX_VALUE is 1 less than MIN_VALUE, and if the result carries MIN_INT to the end the -MIN_INT is out of the upper bound of te int.
            }

            result = result * 10 + digit; // update the int var
            i++;
        }

        return isNeg ? -result : result;

    }
}
