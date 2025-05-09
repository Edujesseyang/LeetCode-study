package src.SlidingWindow;

import java.util.Arrays;

public class DefuseTheBomb_1652 {
    /*
    You have a bomb to defuse, and your time is running out!

Your informer will provide you with a circular array code of length of n and a key k.

To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.

If k > 0, replace the ith number with the sum of the next k numbers.
If k < 0, replace the ith number with the sum of the previous k numbers.
If k == 0, replace the ith number with 0.
As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].

Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!



Example 1:

Input: code = [5,7,1,4], k = 3
Output: [12,10,16,13]
Explanation: Each number is replaced by the sum of the next 3 numbers. The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.
Example 2:

Input: code = [1,2,3,4], k = 0
Output: [0,0,0,0]
Explanation: When k is zero, the numbers are replaced by 0.
Example 3:

Input: code = [2,4,9,3], k = -2
Output: [12,5,6,13]
Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. If k is negative, the sum is of the previous numbers.


Constraints:

n == code.length
1 <= n <= 100
1 <= code[i] <= 100
-(n - 1) <= k <= n - 1
    */

    public static void main(String[] args) {
        int[] test1 = {5, 7, 1, 4};
        int[] test2 = {1, 2, 3, 4};
        int[] test3 = {2, 4, 9, 3};

        int[] result1 = decryptBest(test1, 3);
        System.out.print("Test 1: ");
        for (int num : result1) {
            System.out.print(num + ", ");
        }
        System.out.println();

        int[] result2 = decrypt2(test2, 0);
        System.out.print("Test 2: ");
        for (int num : result2) {
            System.out.print(num + ", ");
        }
        System.out.println();

        int[] result3 = decryptBest(test3, -2);
        System.out.print("Test 3: ");
        for (int num : result3) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    /**
     * 这个是最好的版本的解法.详细的解释可以看下面一个版本的分解解法.
     * 最重要的部分是初始化sum, 和后面的更新sum的过程
     */
    private static int[] decryptBest(int[] code, int k) {
        int codeLen = code.length;
        int[] result = new int[codeLen];
        if (k == 0) {
            return result;
        }
        int sum = 0;
        boolean isNeg = k < 0;
        k = Math.abs(k);

        for (int i = 0; i < k; i++) {
            if (isNeg) {
                sum += code[(i - k + codeLen) % codeLen]; // 负数版本的算法, 从i退后k位开始加k位
            } else {
                sum += code[(i + 1) % codeLen]; // 正数版本的sum 是从i的后一位往后加k位
            }
        } // initialize sum value

        for (int i = 0; i < codeLen; i++) {
            result[i] = sum;
            if (isNeg) {
                // 负数版本的窗口更新为, i位加入窗口, i退后k位退出窗口.
                sum += code[(i + codeLen) % codeLen] - code[(i - k + codeLen) % codeLen];
            } else {
                // 正数版本是i往后数k位的下一位进入窗口, i的下一位退出窗口.因为下一个循环, i要变成i的下一位
                sum += code[(i + k + 1) % codeLen] - code[(i + 1) % codeLen];
            }
        }
        return result;
    }

    /**
     * 这个是详细的滑动窗口的解法, 利用 % 取模运算来模拟循环.
     * 比如 i = 1, len = 5, k = 2 时, 要找i - k的位置会得到一个负数, 这是要只有加上len就得到了最后一位.
     * 用 (i - k + len) % len, 可以更加确切的找到循环以后的位置.
     *
     *
     */
    public int[] decrypt(int[] code, int k) {
        return (k >= 0) ? isPos(code, k) : isNeg(code, -k);
    }

    private static int[] isPos(int[] code, int k) {
        int codeLen = code.length;
        int[] result = new int[codeLen];
        int sum = 0;

        for (int i = 1; i <= k; i++) {
            sum += code[i % codeLen]; // 将下k位直接加到sum
        } // initialize sum value

        for (int i = 0; i < codeLen; i++) {
            result[i] = sum;
            // 更新 sum, 因为窗口向前移动了1位, 所以要加上新加入的一位再减去失去的一位, i 后面的 k+1 位是新加入窗口的, i 的下一位是要离开窗口的
            sum += code[(i + k + 1) % codeLen] - code[(i + 1) % codeLen];
        }
        return result;
    }

    private static int[] isNeg(int[] code, int k) {
        int codeLen = code.length;
        int[] result = new int[codeLen];
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += code[(i - k + codeLen) % codeLen]; // 应该从 i位往后减k位开始加, 用取模法保证位置正确
        } // initialize sum value

        for (int i = 0; i < codeLen; i++) {
            result[i] = sum;
            // 更新sum, 窗口向前移动, i的位置新加入了窗口, i-k位离开了窗口
            sum += code[(i + codeLen) % codeLen] - code[(i - k + codeLen) % codeLen];
        }
        return result;
    }

    /**
     * 以下是笨办法, 思路是先把array展开成为一个不会rollover的array.
     * 然后在上面做滑动窗口
     * @param code unsorted
     * @param k int
     * @return int[]
     */
    private static int[] decrypt2(int[] code, int k) {
        int[] result = new int[code.length];

        if (k > 0) {
            result = kisPos(code, k);
        } else if (k < 0) {
            result = kisNeg(code, k);
        } else {
            Arrays.fill(result, 0);
        }

        return result;
    }

    private static int[] kisPos(int[] code, int k) {
        int codeLen = code.length;
        int[] extendedCode = Arrays.copyOf(code, codeLen + k);

        System.arraycopy(code, 0, extendedCode, codeLen, k);

        int[] result = new int[codeLen];
        int sum = 0;
        int j = 0;
        for (int i = 1; i < codeLen + k; i++) {
            sum += extendedCode[i];
            if (i - j == k) {
                result[j++] = sum;
                sum -= extendedCode[j];
            }
        }
        return result;
    }

    private static int[] kisNeg(int[] code, int kNeg) {
        int k = Math.abs(kNeg);
        int codeLen = code.length;
        int[] extendedCode = new int[codeLen + k];

        int originalIndex = codeLen - 1;
        for (int i = codeLen + k - 1; i >= 0; i--) {
            if (originalIndex < 0) {
                originalIndex = codeLen - 1;
            }
            extendedCode[i] = code[originalIndex--];
        }

        int[] result = new int[codeLen];
        int sum = 0;
        int j = codeLen + k - 1;
        int result_i = codeLen - 1;
        for (int i = codeLen + k - 2; i >= 0; i--) {
            sum += extendedCode[i];
            if (j - i == k) {
                result[result_i--] = sum;
                j--;
                sum -= extendedCode[j];
            }
        }
        return result;
    }
}
