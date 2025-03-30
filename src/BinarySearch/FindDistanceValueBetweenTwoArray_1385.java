package src.BinarySearch;

import java.util.Arrays;

public class FindDistanceValueBetweenTwoArray_1385 {
    /*
    Given two integer arrays arr1 and arr2, and the integer d, return the distance value between the two arrays.

The distance value is defined as the number of elements arr1[i] such that there is not any element arr2[j] where |arr1[i]-arr2[j]| <= d.



Example 1:

Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
Output: 2
Explanation:
For arr1[0]=4 we have:
|4-10|=6 > d=2
|4-9|=5 > d=2
|4-1|=3 > d=2
|4-8|=4 > d=2
For arr1[1]=5 we have:
|5-10|=5 > d=2
|5-9|=4 > d=2
|5-1|=4 > d=2
|5-8|=3 > d=2
For arr1[2]=8 we have:
|8-10|=2 <= d=2    (too close)
|8-9|=1 <= d=2
|8-1|=7 > d=2
|8-8|=0 <= d=2
Example 2:

Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
Output: 2
Example 3:

Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
Output: 1


Constraints:

1 <= arr1.length, arr2.length <= 500
-1000 <= arr1[i], arr2[j] <= 1000
0 <= d <= 100
    */


    public static void main(String[] args) {
        int[] test1 = {1, 4, 2, 3};
        int[] test2 = {-4, -3, 6, 10, 20, 30};

        System.out.println("Test: output: " + findDistance(test1, test2, 3) + " Expected: 2");

    }

    private static int findDistance(int[] nums1, int[] nums2, int distance) {

        Arrays.sort(nums2);
        int count = 0;
        for (int num : nums1) {
            if (isValidDistance(nums2, num, distance)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValidDistance(int[] nums, int target, int distance) {
        int left = 0, right = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target - distance) {
                left = mid + 1;
            } else if (nums[mid] > target + distance) {
                right = mid - 1;
            } else {
                return false;
            }
        }
        return true;
    }

    /*
    可以俩个for 套着暴力解, 不推荐. O(N^2)
    一个for中间一个BS 比较优 O(NlogN)
    核心思路是先sort要比对的那个array, 然后linear loop array1, 每一个element都看看在arr2中这个range里面有没有出现数字, 如果没有就count++.
    */
}
