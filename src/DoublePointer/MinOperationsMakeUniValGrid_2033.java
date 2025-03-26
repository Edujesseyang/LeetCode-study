package src.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinOperationsMakeUniValGrid_2033 {
    /*You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.

A uni-value grid is a grid where all the elements of it are equal.

Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.



Example 1:


Input: grid = [[2,4],[6,8]], x = 2
Output: 4
Explanation: We can make every element equal to 4 by doing the following:
- Add x to 2 once.
- Subtract x from 6 once.
- Subtract x from 8 twice.
A total of 4 operations were used.
Example 2:


Input: grid = [[1,5],[2,3]], x = 1
Output: 5
Explanation: We can make every element equal to 3.
Example 3:


Input: grid = [[1,2],[3,4]], x = 2
Output: -1
Explanation: It is impossible to make every element equal.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 105
1 <= m * n <= 105
1 <= x, grid[i][j] <= 104
*/
    public static void main(String[] args) {
        int[][] test1 = {{6, 8}, {2, 4}};
        int[][] test2 = {{1, 2}, {3, 4}};
        int[][] test3 = {{1}, {1}};
        int[][] test4 = {{931, 128}, {639, 712}};
        int[][] test5 = {{1}};

        List<int[][]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3, test4, test5));
        int[] xs = {2, 2, 1, 73, 1};
        int[] answers = {4, -1, 0, 12, 0};
        for (int i = 0; i < xs.length; i++) {
            System.out.println("Test " + (i + 1) + ": output : " + minOperations(tests.get(i), xs[i]) + " expected: " + answers[i]);
        }
    }

    private static int minOperations(int[][] grid, int x) {
        int[] arr = flattenGrid(grid);
        Arrays.sort(arr);

        int arrLen = arr.length;

        // key is here: find the mid-point of the array, use them to be the targets.
        int mid = arrLen / 2; // for odd num of elements, mid is at the direct mid-point.

        // if there is odd num of elements, return the steps with mid.
        if (arrLen % 2 != 0 || mid == arrLen - 1) {
            return findSteps(arr, x, mid);
        }
        // if there are even num of elements, we need to compare the mid and mid + 1;
        // finally, return the smallest steps between mid and mid + 1;
        return Math.min(findSteps(arr, x, mid), findSteps(arr, x, mid + 1));
    }

    /**
     * Convert a 2-d grid to a 1-d array
     * @param grid : 2-d nested int array
     * @return : 1-d int array.
     */
    private static int[] flattenGrid(int[][] grid) {
        int size = 0;
        for (int[] nums : grid) {
            for (int num : nums) {
                size++;
            }
        }

        int[] arr = new int[size];
        int arr_i = 0;
        for (int[] nums : grid) {
            for (int num : nums) {
                arr[arr_i++] = num;
            }
        }
        return arr;
    }

    /**
     * Find the total steps to make an array a uni-valued array.
     * @param arr : a sorted int array.
     * @param x : the length of each step.
     * @param mid : the mid-index.
     * @return : total steps needs.
     */
    private static int findSteps(int[] arr, int x, int mid) {
        int steps = 0;
        for (int num : arr) {
            if ((arr[mid] - num) % x != 0) {
                return -1;
            }
            steps += Math.abs(arr[mid] - num) / x;
        }
        return steps;
    }

    //关键点:
    // 1. 二维可以压平,如果不是必须在二维操作.
    // 2. sort可以提高效率.
    // 3. 找到在array 中的中点. 累加每一个element到中点那个数的步数.
    // 4. 如果中点为两个, 分别计算并取小的那个.
}
