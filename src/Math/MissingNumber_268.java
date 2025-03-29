package src.Math;

public class MissingNumber_268 {
    /*
    Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.



Example 1:

Input: nums = [3,0,1]

Output: 2

Explanation:

n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.

Example 2:

Input: nums = [0,1]

Output: 2

Explanation:

n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.

Example 3:

Input: nums = [9,6,4,2,3,5,7,0,1]

Output: 8

Explanation:

n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
    */

    public static void main(String[] args) {
        int[] test1 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int[] test2 = {0, 1};

        System.out.println("Test 1: Output: " + findMissingNum(test1) + " Expected: 8");
        System.out.println("Test 2: Output: " + findMissingNum(test2) + " Expected: 3");

    }

    /**
     * TC: O(n)
     * SC: O(1)
     * @param nums : un-sorted int[] arr
     * @return : int, the missing num of the arr
     */
    private static int findMissingNum(int[] nums) {
        int len = nums.length;
        int expectSum = len * (len + 1) / 2; // 等差数列求和公式
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectSum - actualSum;
    }

    /*
    经典数学应用题, 思路, 利用等差数列的特性, 将其和减去数列里实际数字的总和就等于missing的那个数字.
    */

}
