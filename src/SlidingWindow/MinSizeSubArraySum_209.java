package src.SlidingWindow;

public class MinSizeSubArraySum_209 {
    /*
    Given an array of positive integers nums and a positive integer target,
    return the minimal length of a subarray whose sum is greater than or equal to target.
    If there is no such subarray, return 0 instead.



Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0


Constraints:

1 <= target <= 109
1 <= nums.length <= 105
1 <= nums[i] <= 104


Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
    */

    public static void main(String[] args) {
        int[] test1 = {2, 3, 1, 2, 4, 3};
        int[] test2 = {1, 4, 4};
        int[] test3 = {1, 1, 1, 1, 1, 1, 1, 1};

        System.out.println("Test 1: Output: " + findMinSizeSubArray(test1, 7) + " Expected: 2");
        System.out.println("Test 2: Output: " + findMinSizeSubArray(test2, 4) + " Expected: 1");
        System.out.println("Test 3: Output: " + findMinSizeSubArray(test3, 11) + " Expected: 0");

    }

    /**
     * 一个滑动窗口的典型题, 注意中间的while的用法, 和最后是否足够大的判断.
     * @param nums unsorted
     * @param target int
     * @return int
     */
    private static int findMinSizeSubArray(int[] nums, int target) {
        int result = nums.length; // 初始化结果为最长结果
        int slow_p = 0; // 建立一个慢针
        int subArraySum = 0; // 建立一个存subArray的和的int
        boolean isBigEnough = false; // 建立一个判断, input array的总和是否超过target
        for (int fast_p = 0; fast_p < nums.length; fast_p++) { // 遍历整个input array
            subArraySum += nums[fast_p]; // 把快针的值加入subArray的和中
            // 这里用while是精髓, 因为如果出现了如 1, 1, 1, 1, 1, 100, 1, .... 的情况, 如果用if而不是while, 当快针更新到100时, 假如target=100,
            // 这时sum = 105 >= 100, 然后进入下一个 loop 循环,  然后快针指向1, sum = 105, 继续更新, 最终无法正确找到当只有一个100 >= 100的这种最小特殊情况.
            // 所以用while, 一种缩进慢针, 直到sum小于target为止, 再进入下一个循环, 此时就正确找到了单100 的情况.
            while (subArraySum >= target) { // 如果该和大于了target, 则不断的
                result = Math.min(result, fast_p - slow_p + 1); // 将快针到慢针的距离更新到结果
                subArraySum -= nums[slow_p++]; // 不断的更新慢针并慢针指的数从subarray sum中减掉
                isBigEnough = true; // 并更新判断值
            }
        }
        return isBigEnough ? result : 0;
    }
}
