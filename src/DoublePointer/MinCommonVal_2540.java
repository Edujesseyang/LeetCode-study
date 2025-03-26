package src.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinCommonVal_2540 {
    /*
    * Given two integer arrays nums1 and nums2, sorted in non-decreasing order,
    * return the minimum integer common to both arrays.
    * If there is no common integer amongst nums1 and nums2, return -1.

Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.



Example 1:

Input: nums1 = [1,2,3], nums2 = [2,4]
Output: 2
Explanation: The smallest element common to both arrays is 2, so we return 2.
Example 2:

Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
Output: 2
Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.


Constraints:

1 <= nums1.length, nums2.length <= 105
1 <= nums1[i], nums2[j] <= 109
Both nums1 and nums2 are sorted in non-decreasing order.*/

    public static void main(String[] args) {
        int[] test1 = {0, 1, 2, 5, 9, 81, 230};
        int[] test2 = {5, 6, 87, 230, 561, 4555};
        int[] test3 = {57, 69, 89, 91, 255, 263};
        int[] test4 = {7, 9, 9, 9, 255, 263};

        List<int[]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3, test4));

        for (int[] arr : tests) {
            for (int i = 0; i < tests.size(); i++) {
                System.out.print("Arr_1 = ");
                printArr(arr);
                System.out.print("Arr_2 = ");
                printArr(tests.get(i));
                System.out.println("Output: " + findMinCommonVal(arr, tests.get(i)) + "\n");
            }
        }

    }

    /**
     * @param arr1 int[] array 1: a sorted int array smallest to largest
     * @param arr2 int[] array 2: a sorted int array smallest to largest
     * @return int the first common number in array 1 and array 2;
     */
    private static int findMinCommonVal(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] > arr2[j]) {
                j++;
            } else if (arr2[j] > arr1[i]) {
                i++;
            } else {
                return arr1[i];
            }
        }

        return -1;
    }

    private static void printArr(int[] arr) {
        System.out.print("{ " + arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print(" , " + arr[i]);
        }
        System.out.println("}");
    }

    /*
    关键点:
    1. 双指针分别用于两个arr, 别混用.
    2. 判断时让小的那一方增加.相同就return.
     */
}
