package src.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTargetIndicesAfterSortingArray_2089 {
    /*You are given a 0-indexed integer array nums and a target element target.

    A target index is an index i such that nums[i] == target.

    Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.



            Example 1:

    Input: nums = [1,2,5,2,3], target = 2
    Output: [1,2]
    Explanation: After sorting, nums is [1,2,2,3,5].
    The indices where nums[i] == 2 are 1 and 2.
    Example 2:

    Input: nums = [1,2,5,2,3], target = 3
    Output: [3]
    Explanation: After sorting, nums is [1,2,2,3,5].
    The index where nums[i] == 3 is 3.
    Example 3:

    Input: nums = [1,2,5,2,3], target = 5
    Output: [4]
    Explanation: After sorting, nums is [1,2,2,3,5].
    The index where nums[i] == 5 is 4.


    Constraints:

            1 <= nums.length <= 100
            1 <= nums[i], target <= 100

     */

    public static void main(String[] args) {
        int[] test1 = {1, 2, 5, 2, 3};
        int[] test2 = {1, 2, 5, 2, 3};
        int[] test3 = {1, 2, 5, 2, 3};
        System.out.println("Test 1: Target=2   Output: " + targetIndices(test1, 2));
        System.out.println("Test 2: Target=3   Output: " + targetIndices2(test2, 3));
        System.out.println("Test 3: Target=5   Output: " + targetIndices3(test3, 5));

    }

    /**
     * Approach 1: O(NLogN + N)
     * linear search after sorted
     * @param nums int[]
     * @param target int
     * @return List<Integer>
     */
    private static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        return result;
        /*
        这个没什么说的, 简单直接, sort 一下, 然后linear search 来数target就行了.
        */
    }

    /**
     * Approach 2: O(NlogN + 2logN)
     * @param nums int[]
     * @param target int
     * @return List<Integer>
     */
    private static List<Integer> targetIndices2(int[] nums, int target) {
        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int firstTarget = left;

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int lastTarget = right;

        List<Integer> result = new ArrayList<>();

        while (firstTarget <= lastTarget) {
            result.add(firstTarget++);
        }

        return result;

        /*
        有点多此一举的, sort本身就是nlogn了, sort完再用BS也没什么意义.
        */
    }

    /**
     * Best approach: O(n + k), k is the number of targets
     * @param nums int[]
     * @param target int
     * @return List
     *
     * 重点是这个, 不用sort, 绝了
     */
    private static List<Integer> targetIndices3(int[] nums, int target) {

        // 点定义两个临时数, 一个是记录一个target出现了几次
        int targetCount = 0;
        // 第二个是记录有几个比target还小的数字
        int numsInLeft = 0;

        // loop 一次更改这两个记录.
        for (int i : nums) {
            if (nums[i] == target) {
                targetCount++;
            } else if (nums[i] < target) {
                numsInLeft++;
            }
        }
        // 建一个List来接答案
        List<Integer> result = new ArrayList<>();

        // 这个是精妙之所在, sort完的target肯定从它前面所以数字之和的下一位开始出现,
        // 那么就从target之前的那一个数的index递增targetCount次就可以了. 精妙.
        while (targetCount > 0) {
            result.add(numsInLeft++); // numsInLeft 其实等于sort后, target之前的那个数的index, 直接++, targetCount次.
            targetCount--;
        }

        return result;
    }
}
