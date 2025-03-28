package src.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxCountOfPosOrNegInArray_2529 {
    /*Given an array nums sorted in non-decreasing order,
    return the maximum between the number of positive integers and the number of negative integers.

In other words, if the number of positive integers in nums is pos and the number of negative integers is neg,
then return the maximum of pos and neg.
Note that 0 is neither positive nor negative.



Example 1:

Input: nums = [-2,-1,-1,1,2,3]
Output: 3
Explanation: There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
Example 2:

Input: nums = [-3,-2,-1,0,0,1,2]
Output: 3
Explanation: There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
Example 3:

Input: nums = [5,20,66,1314]
Output: 4
Explanation: There are 4 positive integers and 0 negative integers. The maximum count among them is 4.


Constraints:

1 <= nums.length <= 2000
-2000 <= nums[i] <= 2000
nums is sorted in a non-decreasing order.*/

    public static void main(String[] agrs) {
        int[] test1 = {-2, -1, -1, 1, 2, 3};
        int[] test2 = {-3, -2, -1, 0, 0, 1, 2};
        int[] test3 = {5, 20, 66, 1314};
        int[] test4 = {-1};
        int[] test5 = {0};
        int[] test6 = {0, 0};

        List<int[]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3, test4, test5, test6));
        int[] result = {3, 3, 4, 1, 0, 0};

        for (int i = 0; i < result.length; i++) {
            System.out.println("Test " + (i + 1));
            print(tests.get(i));
            System.out.println("\nOutput: " + appocah1(tests.get(i)) + " Expected: " + result[i] + "\n");
        }
    }

    private static int appocah1(int[] nums) {
        int len = nums.length;

        // find the first non-neg number
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // left is the key, typically binary search, left is pointing the place that the number you are looking for
        int negCount = left;

        // find the last non-neg number
        left = 0;
        right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // left is the key, typically binary search, left is pointing the place that the number you are looking for
        int posCount = len - left;

        // find the max count
        return Math.max(negCount, posCount);
    }

    private static void print(int[] arr) {
        for (int j : arr) {
            System.out.print(j + ", ");
        }
    }
}
