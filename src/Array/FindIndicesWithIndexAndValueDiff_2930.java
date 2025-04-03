package src.Array;

public class FindIndicesWithIndexAndValueDiff_2930 {
    /*
    * You are given a 0-indexed integer array nums having length n, an integer indexDifference, and an integer valueDifference.

Your task is to find two indices i and j, both in the range [0, n - 1], that satisfy the following conditions:

abs(i - j) >= indexDifference, and
abs(nums[i] - nums[j]) >= valueDifference
Return an integer array answer, where answer = [i, j] if there are two such indices, and answer = [-1, -1] otherwise.
* If there are multiple choices for the two indices, return any of them.

Note: i and j may be equal.



Example 1:

Input: nums = [5,1,4,1], indexDifference = 2, valueDifference = 4
Output: [0,3]
Explanation: In this example, i = 0 and j = 3 can be selected.
abs(0 - 3) >= 2 and abs(nums[0] - nums[3]) >= 4.
Hence, a valid answer is [0,3].
[3,0] is also a valid answer.
Example 2:

Input: nums = [2,1], indexDifference = 0, valueDifference = 0
Output: [0,0]
Explanation: In this example, i = 0 and j = 0 can be selected.
abs(0 - 0) >= 0 and abs(nums[0] - nums[0]) >= 0.
Hence, a valid answer is [0,0].
Other valid answers are [0,1], [1,0], and [1,1].
Example 3:

Input: nums = [1,2,3], indexDifference = 2, valueDifference = 4
Output: [-1,-1]
Explanation: In this example, it can be shown that it is impossible to find two indices that satisfy both conditions.
Hence, [-1,-1] is returned.


Constraints:

1 <= n == nums.length <= 100
0 <= nums[i] <= 50
0 <= indexDifference <= 100
0 <= valueDifference <= 50*/

    public static void main(String[] args) {
        int[] test = {5, 1, 4, 1};
        System.out.print("Output: ");
        int[] ans = findIndices(test, 2, 4);
        System.out.print("[" + ans[0] + ", " + ans[1] + "]");
        System.out.println(" Expected: [0, 3]");

        int[] test2 = {31, 23, 36};
        System.out.print("Output: ");
        int[] ans2 = findIndices(test2, 1, 11);
        System.out.print("[" + ans2[0] + ", " + ans2[1] + "]");
        System.out.println(" Expected: [1, 2]");
    }

    private static int[] findIndices(int[] nums, int indDiff, int valDiff) {
        for (int i = 0; i < nums.length - indDiff; i++) {
            for (int j = i + indDiff; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) >= valDiff) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /*
    此题出现在双指针里, 其实基本用不上双指针, 也用不上滑动窗口, 基本上是一个暴力解的题. 注意就是输出对index有要求, 那么index其实有规律可以利用,
    所以这里我们只有找距离不小于indDiff的数来做比较就可以了, 基本上不用可以写判断来确认index是否符合条件

    * */
}
