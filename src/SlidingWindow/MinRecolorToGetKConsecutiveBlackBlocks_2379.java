package src.SlidingWindow;

public class MinRecolorToGetKConsecutiveBlackBlocks_2379 {
    /*
    You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B',
    representing the color of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.

You are also given an integer k, which is the desired number of consecutive black blocks.

In one operation, you can recolor a white block such that it becomes a black block.

Return the minimum number of operations needed such that there is at least one occurrence of k consecutive black blocks.



Example 1:

Input: blocks = "WBBWWBBWBW", k = 7
Output: 3
Explanation:
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
so that blocks = "BBBBBBBWBW".
It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
Therefore, we return 3.
Example 2:

Input: blocks = "WBWBBBW", k = 2
Output: 0
Explanation:
No changes need to be made, since 2 consecutive black blocks already exist.
Therefore, we return 0.


Constraints:

n == blocks.length
1 <= n <= 100
blocks[i] is either 'W' or 'B'.
1 <= k <= n
    */

    public static void main(String[] args) {
        String test = "WBBWWBBWBW";
        String test2 = "WBWBBBW";
        System.out.println("Test 1: Output: " + findMinRecolor(test, 7) + " Expected: 3");
        System.out.println("Test 2: Output: " + findMinRecolor(test2, 2) + " Expected: 0");
    }

    /**
     * 此题是个典型的固定长度滑动窗口题, 解题思路:
     * 首先考虑窗口边框, 固定为左右差k即可.
     * 其次考虑窗口内维护什么, 这里是要得到全'B'的subArray需要改变的'W'的数量,所以维护的是W的count.
     * 然后考虑如何实现, 那就简单了, 只要数一下前k个里面右多少个W, 然后初始化窗口. 接着滑动余下部分, 进入的是W, 则count++, 离开的是W则count--.
     * 最后每次改变窗口之前结算一个就可以了.
     * @param blocks String
     * @param k int
     * @return int
     */
    private static int findMinRecolor(String blocks, int k) {
        int whiteCount = 0;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
        } // 初始化进loop前的白色数量

        int minWhite = whiteCount;
        for (int i = k; i < blocks.length(); i++) { // 滑动剩下部分
            if (blocks.charAt(i) == 'W') { // 当进入窗口的是W, count++
                whiteCount++;
            }
            if (blocks.charAt(i - k) == 'W') { // 当离开窗口的是W, count--
                whiteCount--;
            }
            minWhite = Math.min(minWhite, whiteCount); // 更新最小count值.
        }
        return minWhite;
    }
    // 注: 固定窗口不必要用两个指针, i和i-k 就足够了
}
