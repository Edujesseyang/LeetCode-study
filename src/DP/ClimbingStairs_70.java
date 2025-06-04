package src.DP;

public class ClimbingStairs_70 {
    /*
    You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45
    */

    public static void main(String[] args) {
        System.out.println("Climb 54 stairs have '" + climbStairs(54) + "' ways.");
    }

    /**
     * 这一题的的结构是一个典型的Fibonacci数列的抽象结构. 每增加一级台阶就相当于将前两级的总和.
     * 所以我们可以直接用Fibonacci number的动态规划解法直接套在这一题上面.
     */
    private static int climbStairs(int n) {
        if (n <= 3) {
            return n;
        }

        int prev = 3, prevPrev = 2, current = 0;
        for (int i = 4; i <= n; i++) {
            current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }

        return current;
    }
}
