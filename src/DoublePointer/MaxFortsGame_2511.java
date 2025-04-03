package src.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxFortsGame_2511 {
    /*
    * You are given a 0-indexed integer array forts of length n representing the positions of several forts.
    * forts[i] can be -1, 0, or 1 where:

-1 represents there is no fort at the ith position.
0 indicates there is an enemy fort at the ith position.
1 indicates the fort at the ith the position is under your command.
Now you have decided to move your army from one of your forts at position i to an empty position j such that:

0 <= i, j <= n - 1
The army travels over enemy forts only. Formally, for all k where min(i,j) < k < max(i,j), forts[k] == 0.
While moving the army, all the enemy forts that come in the way are captured.

Return the maximum number of enemy forts that can be captured. In case it is impossible to move your army,
* or you do not have any fort under your command, return 0.



Example 1:

Input: forts = [1,0,0,-1,0,0,0,0,1]
Output: 4
Explanation:
- Moving the army from position 0 to position 3 captures 2 enemy forts, at 1 and 2.
- Moving the army from position 8 to position 3 captures 4 enemy forts.
Since 4 is the maximum number of enemy forts that can be captured, we return 4.
Example 2:

Input: forts = [0,0,1,-1]
Output: 0
Explanation: Since no enemy fort can be captured, 0 is returned.


Constraints:

1 <= forts.length <= 1000
-1 <= forts[i] <= 1*/

    public static void main(String[] args) {
        int[] test1 = {0, 1, 0, 0, -1, 0, 0, 1};
        int[] test2 = {1, 0, 0, 0, -1, 0, 0, -1, 0, 1};
        int[] test3 = {0, 0, 0, 1, -1};


        List<int[]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3));
        int[] answer = {2, 3, 0};

        for (int i = 0; i < answer.length; i++) {
            System.out.println("Test " + (i + 1) + ": output: " + maxForts(tests.get(i)) + " expected: " + answer[i]);
        }
    }

    private static int maxForts(int[] arr) {
        int max = 0; // 初始max
        int prev = -999; // 默认设慢针为-999 或者 -任何数, 避免使用-1, 防止和题面的-1 混淆

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) { // 如果快针指的是 1 或 -1
                if (prev != -999 && arr[i] != arr[prev]) { // 如果prev已经改变,并且快针指的不等于慢针指的
                    max = Math.max(max, i - prev - 1); // update max
                }
                prev = i; // 更新慢针到快针处
            }
        }
        return max;
    }

    /*
     * 注意:
     * 此题很绕, 注意双指针的逻辑, 保持快慢针的规律, 放弃用|a-b|的奇怪想法.
     * 用i当快针, 设一个慢针, 然后判断几个条件;
     * loop: 只有如果快针是1 或 -1 的时候才进入逻辑判断.如果是0 则直接把慢针更新为i
     * 以后的圈, 主逻辑: 如果prev不是初始值时, 并且快针值和慢针不同时, 怎更新max.
     *
     * 不一定非要一个针指1一个指-1, 可以想象只要这两个针指的不一样, 无所谓具体是什么
     * 不要想太复杂:
     * 这类题只需要：

            找到两个相邻的非0位置

            判断它们是不是敌我对立（1 vs -1）

            然后看它们中间隔了多少个 0
            *
        PS: 一个关键技术, 不一定非得初始化双指针为0, 可以先初始化为负值, 判断element的状态时再更新.
        * 指针可以初始化为任意哨兵值，用于延迟判断或简化状态控制
     * */
}
