package src.Array;

import java.util.*;

public class MaxPairSumInAnArray_2815 {
    //You are given an integer array nums. You have to find the maximum sum of a pair of numbers from nums such that the largest digit in both numbers is equal.
    //
    //For example, 2373 is made up of three distinct digits: 2, 3, and 7, where 7 is the largest among them.
    //
    //Return the maximum sum or -1 if no such pair exists.
    //
    //
    //
    //Example 1:
    //
    //Input: nums = [112,131,411]
    //
    //Output: -1
    //
    //Explanation:
    //
    //Each numbers largest digit in order is [2,3,4].
    //
    //Example 2:
    //
    //Input: nums = [2536,1613,3366,162]
    //
    //Output: 5902
    //
    //Explanation:
    //
    //All the numbers have 6 as their largest digit, so the answer is 2536 + 3366 = 5902.
    //
    //Example 3:
    //
    //Input: nums = [51,71,17,24,42]
    //
    //Output: 88
    //
    //Explanation:
    //
    //Each number's largest digit in order is [5,7,7,4,4].
    //
    //So we have only two possible pairs, 71 + 17 = 88 and 24 + 42 = 66.
    //
    //
    //
    //Constraints:
    //
    //2 <= nums.length <= 100
    //1 <= nums[i] <= 104

    public static void main(String[] args) {
        int[] test1 = {144, 223, 345, 412};
        int[] test2 = {1564, 2329, 396, 409};

        System.out.println("two buckets tracking:");
        System.out.println("Answer 1: " + maxSum(test1));
        System.out.println("Answer 2: " + maxSum(test2));
        System.out.println("10 heaps adding:");
        System.out.println("Answer 1: " + findMaxSum(test1));
        System.out.println("Answer 2: " + findMaxSum(test2));
    }


    // way 1: two backers tracking
    private static int findMaxSum(int[] nums) {
        int[] largest = new int[10];
        Arrays.fill(largest, Integer.MIN_VALUE);
        int[] secondLargest = new int[10];
        Arrays.fill(secondLargest, Integer.MIN_VALUE);

        for(int i : nums){
            int ind = largestDigit(i);
            if(i > largest[ind]){
                secondLargest[ind] = largest[ind];
                largest[ind] = i;
            } else if(i > secondLargest[ind]){
                secondLargest[ind] = i;
            }
        }

        int max = -1;
        for(int i = 0; i < 10; i++){
            if(largest[i] == Integer.MIN_VALUE || secondLargest[i] == Integer.MIN_VALUE) continue;
            int sum = largest[i] + secondLargest[i];
            max = Math.max(max, sum);
        }

        return max;
    }

    // way 2:  10 heap adding
    private static int maxSum(int[] nums) {
        int ans = -1;
        List<PriorityQueue<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            matrix.add(new PriorityQueue<>(Comparator.reverseOrder()));
        }

        for (int i : nums) {
            int ind = largestDigit(i);
            matrix.get(ind).offer(i);
        }

        for (PriorityQueue<Integer> que : matrix) {
            if (que == null || que.size() < 2) continue;
            int last = que.poll();
            int lastSec = que.poll();
            ans = Math.max(ans, last + lastSec);
        }

        return ans;
    }

    private static int largestDigit(int num) {
        int res = 0;
        while (num > 0) {
            int digit = num % 10;
            res = Math.max(res, digit);
            num /= 10;
        }
        return res;
    }
}

