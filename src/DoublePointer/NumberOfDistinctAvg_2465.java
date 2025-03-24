package src.DoublePointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctAvg_2465 {
    /*You are given a 0-indexed integer array nums of even length.

            As long as nums is not empty, you must repetitively:

    Find the minimum number in nums and remove it.
    Find the maximum number in nums and remove it.
    Calculate the average of the two removed numbers.
    The average of two numbers a and b is (a + b) / 2.

    For example, the average of 2 and 3 is (2 + 3) / 2 = 2.5.
    Return the number of distinct averages calculated using the above process.

    Note that when there is a tie for a minimum or maximum number, any can be removed.



    Example 1:

    Input: nums = [4,1,4,0,3,5]
    Output: 2
    Explanation:
            1. Remove 0 and 5, and the average is (0 + 5) / 2 = 2.5. Now, nums = [4,1,4,3].
            2. Remove 1 and 4. The average is (1 + 4) / 2 = 2.5, and nums = [4,3].
            3. Remove 3 and 4, and the average is (3 + 4) / 2 = 3.5.
    Since there are 2 distinct numbers among 2.5, 2.5, and 3.5, we return 2.
    Example 2:

    Input: nums = [1,100]
    Output: 1
    Explanation:
    There is only one average to be calculated after removing 1 and 100, so we return 1.


    Constraints:

            2 <= nums.length <= 100
    nums.length is even.
    0 <= nums[i] <= 100
    */
    public static void main(String[] args) {
        int[] test1 = {4, 1, 4, 0, 3, 5};
        int[] test2 = {1, 0};
        int[] test3 = {2, 4, 5, 9, 6, 4, 3, 1, 8, 10};

        System.out.println("Test 1: Output 1: " + disinctAvg(test1) + ", expected: 2");
        System.out.println("Test 2: Output 2: " + disinctAvg(test2) + ", expected: 1");
        System.out.println("Test 3: Output 3: " + disinctAvg(test3) + ", expected: 3");
    }


    public static int disinctAvg(int[] nums) {
        Arrays.sort(nums);

        Set<Double> result = new HashSet<>();
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            result.add((nums[left] + nums[right]) / 2.0);
            left++;
            right--;
        }
        return result.size();
    }

    /*
    * 经典双指针, 没啥好说的.
    * 方法论:
    * 碰见无从下手的int array, 先sort一个. 再想辙.
    * 另外, 运用
    * */
}
