package src.DoublePointer;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber_202 {
    /*
    Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.



Example 1:

Input: n = 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Example 2:

Input: n = 2
Output: false


Constraints:

1 <= n <= 231 - 1
    */

    public static void main(String[] args) {
        int[] happyNums = {1, 7, 10, 13, 19};
        int[] unhappyNums = {2, 3, 4, 11, 20};
        int[] edgeCases = {1111111, 9999999};

        System.out.println("=== Happy Number 测试 ===");
        for (int n : happyNums) {
            test(n, true);
        }

        System.out.println("=== Unhappy Number 测试 ===");
        for (int n : unhappyNums) {
            test(n, false);
        }

        System.out.println("=== 大数测试 ===");
        for (int n : edgeCases) {
            testAllThree(n); // 不断言，只要三种结果一致
        }
    }

    private static void test(int n, boolean expected) {
        boolean r1 = isHappy1(n);
        boolean r2 = isHappy2(n);
        boolean r3 = isHappy3(n);

        System.out.printf("n = %-8d | expected = %-5s | isHappy1 = %-5s | isHappy2 = %-5s | isHappy3 = %-5s %s\n",
                n, expected, r1, r2, r3,
                (r1 == expected && r2 == expected && r3 == expected) ? "✅" : "❌");
    }

    private static void testAllThree(int n) {
        boolean r1 = isHappy1(n);
        boolean r2 = isHappy2(n);
        boolean r3 = isHappy3(n);

        System.out.printf("n = %-8d | isHappy1 = %-5s | isHappy2 = %-5s | isHappy3 = %-5s %s\n",
                n, r1, r2, r3,
                (r1 == r2 && r2 == r3) ? "✅" : "❌ 差异");
    }



    /* Happy Number 问题的三种解法：
     * 1. 快慢指针（Floyd 判环法） —— 空间 O(1)，适合面试
     * 2. Set 判重 —— 稳定实用，适合真实开发
     * 3. 递归+剪枝+异常控制 —— 教学演示，不推荐上线
     *
     * 重点在于理解：此题的数学结构本质是 “有限状态转移是否形成环”。
     */

    /**
     * Floyd 判环:
     * 算法之美的代表, 算法大神Floyd惊世骇俗之作, 弗洛伊德环形判断.
     * 逻辑非常类似于快慢针判断一个linkedList是否是环.
     * 这一题如果不是happy number, 循环会无限重复下去, sum这个值不会收敛于某个数, 也不会发散到无穷.
     * 所以此题的根本在于找到一种方法可以停止判断, 环判就是其中一种.
     * 这样的无限循环的数学判定本质上就是一个逻辑上的环结构, 我们用双指针, 慢针每次"前进"一步, 而快针每次"前进"两步.
     * 当快针最终遇见了慢针, 就证明已经判断了一圈了, 如果继续没有意义并且无限循环下去.
     */
    private static boolean isHappy1(int n) {
        int slow = n;
        int fast = findSum(n);

        while (fast != 1 && fast != slow) {
            slow = findSum(slow);
            fast = findSum(findSum(fast));
        }
        return fast == 1;
    }

    /**
     * HashSet 判重:
     *  这是另一种发现出现重复结果的办法, 利用set的去重添加功能, 当add结果返回false时, 就意味着set中已经存在该结果了.
     *  继续下去就会无限循环并且毫无意义.
     */
    private static boolean isHappy2(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (!set.add(n)) {
                break;
            }
            n = findSum(n);
        }
        return n == 1;
    }

    /**
     * 概率判:
     * 第三种结束循环的方法, 不稳定, 不可靠, 有风险. 就是靠运气, 用我们已知的几个已经确定的happyNumber和一些未知的happyNumber来作为结束条件.
     * 已知十以内的1和7是happyNum, 其他的都不是, 所以我们靠无限深的递归硬碰这几个数字.
     * 如果碰上了就成功, 如果碰不上, 我们只能靠catch stackOverflowError来结束循环了. 当然我们可以往里面增加数字. 以便增加成功判定的概率.
     */
    private static boolean isHappy3(int n) {
        if (n == 1 || n == 7 || n == 10 || n == 13 || n == 19) {
            return true;
        } else if (n < 20) {
            return false;
        }
        try {
            return isHappy3(findSum(n));
        } catch (StackOverflowError e) {
            return false;
        }
    }

    private static int findSum(int n) {
        int s = 0;
        while (n > 0) {
            int d = n % 10;
            s += d * d;
            n /= 10;
        }
        return s;
    }

}
