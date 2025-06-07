package src.DoublePointer;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_15 {
    /*
    Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.



Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.


Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
    */

    public static void main(String[] args) {
        int[] test = {-1, 4, -5, -6, 4, 3, 6, 1, 3, -6, 0, 6, 4};
        List<List<Integer>> result = threeSum(test);
        for (List<Integer> list : result) {
            System.out.print("[");
            for (int i : list) {
                System.out.print(i + ", ");
            }
            System.out.print("]");
        }
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // sort Array
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>(); // define answer variable

        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { // skip all duplicates
                continue;
            }

            int l = i + 1, r = len - 1; // from (i + 1) to (len - 1)
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    // build list, add to result, and update pointers
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[l++], nums[r--])));

                    while (l < r && nums[l] == nums[l - 1]) {
                        l++; // skip all duplicates from left side
                    }

                    while (l < r && nums[r] == nums[r + 1]) {
                        r--; // skip all duplicates from right side
                    }
                } else if (sum < 0) { // update left if sum less than 0
                    l++;
                } else if (sum > 0) { // update right if sum greater than 0
                    r--;
                }
            }
        }

        return result;
    }
}

