package src.String;

public class AddBinary_76 {
    /*
    Given two binary strings a and b, return their sum as a binary string.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"


Constraints:

1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.
    */

    public static void main(String[] args) {

    }

    /**
     * 聪明办法:
     * 1. 将carry定义为0或1, 直接可以加法处理carry.
     * 2. 一个loop到底, 通过一个判断来确定哪个string还有剩余. 已完成的string 所代表的num 永远会为0.
     * 3. 用mod来wrap around sum, 取得应该append的数字.
     * 跟新carry直接用除法. 如果sum大于2就会使carry变为1.
     */
    private static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int numA = (i >= 0) ? a.charAt(i--) - '0' : 0;
            int numB = (j >= 0) ? b.charAt(j--) - '0' : 0;
            int sum = numA + numB + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }

        return sb.reverse().toString();
    }


    /**
     * 笨办法!
     */
    private static String addBinary2(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        boolean CarryOver = false;
        while (i >= 0 && j >= 0) {
            int numA = a.charAt(i), numB = b.charAt(j);
            if (numA + numB == 96) {
                if (CarryOver) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
                CarryOver = false;
            } else if (numA + numB == 97) {
                if (CarryOver) {
                    sb.append('0');
                    CarryOver = true;
                } else {
                    sb.append('1');
                    CarryOver = false;
                }
            } else if (numA + numB == 98) {
                if (CarryOver) {
                    sb.append('1');
                    CarryOver = true;
                } else {
                    sb.append('0');
                    CarryOver = true;
                }
            }
            i--;
            j--;
        }

        String rest = aLen > bLen ? a.substring(0, i + 1) : b.substring(0, j + 1);
        i = rest.length() - 1;

        while (i >= 0) {
            if (CarryOver) {
                if (rest.charAt(i) == '0') {
                    sb.append('1');
                    CarryOver = false;
                } else if (rest.charAt(i) == '1') {
                    sb.append('0');
                }
            } else {
                sb.append(rest.charAt(i));
            }
            i--;
        }

        if (CarryOver) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }
}
