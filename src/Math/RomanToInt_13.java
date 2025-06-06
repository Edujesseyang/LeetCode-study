package src.Math;

import java.util.*;

public class RomanToInt_13 {

    /*Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together.
12 is written as XII, which is simply X + II.
The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX.
There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.



Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.


Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].
     */
    public static void main(String[] args) {
        List<String> testCase = new ArrayList<>();
        testCase.add("MCMXCIV");
        testCase.add("LVIII");
        testCase.add("III");
        testCase.add("CCXCIX");

        List<Integer> answers = new ArrayList<>(Arrays.asList(1994, 58, 3, 299));

        int i = 0;
        for (String s : testCase) {
            System.out.println("Test: \"" + s + "\" Answer: " + romanToInt(s) + " Correct Answer: " + answers.get(i));
            i++;
        }

        i = 0;
        for (String s : testCase) {
            System.out.println("Test: \"" + s + "\" Answer: " + romanToInt2(s) + " Correct Answer: " + answers.get(i));
            i++;
        }

        i = 0;
        for (String s : testCase) {
            System.out.println("Test: \"" + s + "\" Answer: " + romanToInt3(s) + " Correct Answer: " + answers.get(i));
            i++;
        }

    }

    private static int romanToInt(String string) {
        Map<Character, Integer> symbolMap = new HashMap<>();
        symbolMap.put('I', 1);
        symbolMap.put('V', 5);
        symbolMap.put('X', 10);
        symbolMap.put('L', 50);
        symbolMap.put('C', 100);
        symbolMap.put('D', 500);
        symbolMap.put('M', 1000);

        int result = 0;
        int len = string.length();
        for (int i = 0; i < len - 1; i++) {
            int current = symbolMap.get(string.charAt(i));
            if (current < symbolMap.get(string.charAt(i + 1))) {
                result -= current;
            } else {
                result += current;
            }
        }
        return result + symbolMap.get(string.charAt(len - 1));
    }

    private static int romanToInt2(String s) {
        int[] cMap = new int[100];
        cMap['I'] = 1;
        cMap['V'] = 5;
        cMap['X'] = 10;
        cMap['L'] = 50;
        cMap['C'] = 100;
        cMap['D'] = 500;
        cMap['M'] = 1000;

        int result = 0;
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            int current = cMap[s.charAt(i)];
            if (current < cMap[s.charAt(i + 1)]) {
                result -= current;
            } else {
                result += current;
            }
        }

        return result + cMap[s.charAt(len - 1)];
    }

    private static int romanToInt3(String s) {
        int len = s.length();
        int ans = 0;

        for (int i = 0; i < len - 1; i++) {
            int current = getInt(s.charAt(i));
            if (current < getInt(s.charAt(i + 1))) {
                ans -= current;
            } else {
                ans += current;
            }
        }

        return ans + getInt(s.charAt(len - 1));
    }

    private static int getInt(char c) {
        return switch (c) {
            case 'I' ->
                1;
            case 'V' ->
                5;
            case 'X' ->
                10;
            case 'L' ->
                50;
            case 'C' ->
                100;
            case 'D' ->
                500;
            case 'M' ->
                1000;
            default ->
                0;
        };
    }

}
