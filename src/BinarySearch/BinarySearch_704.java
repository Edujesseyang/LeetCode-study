package src.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch_704 {
    /*
    Given an array of integers nums which is sorted in ascending order,
    and an integer target, write a function to search target in nums.
    If target exists, then return its index. Otherwise, return -1.

    You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1


Constraints:

1 <= nums.length <= 104
-104 < nums[i], target < 104
All the integers in nums are unique.
nums is sorted in ascending order.*/

    public static void main(String[] args) {
        int[] test1 = {0};
        int[] test2 = {-15, -1, 0, 3, 5, 6, 7, 9, 12, 14, 100};
        int[] test3 = {-5, -1, 0, 1, 2, 3, 5, 9, 10, 11, 12};
        int[] test4 = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] test5 = null;
        List<int[]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3, test4, test5));
        int[] answers = {0, 1, 2, 3, 4};
        for (int i = 0; i < answers.length; i++) {
            System.out.println("Test " + (i + 1) + ": Output: " + binarySearch(tests.get(i), i) + " expected: " + answers[i]);
        }

    }

    private static int binarySearch(int[] nums, int target) {
        // filter out if input is null or empty array
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // define left and right pointers
        int left = 0;
        int right = nums.length - 1;


        // binary searching
        while (left < right) {
            // define a mid-pointer to compare with target
            int mid = left + (right - left) / 2;

            // compare the value that mid is pointing with target
            if (target < nums[mid]) {
                // if the target is in left half, update right bound
                right = mid - 1;
            } else if (target > nums[mid]) {
                // if the target is in right half, update left bound
                left = mid + 1;
            } else {
                // if equal, target found successfully. return mid, it is the index
                return mid;
            }
        }

        // if no found, return -1.
        return -1;
    }

    /*
     * 经典二分查找, 要是不会写就老老实实当兵吧.
     * */
}
