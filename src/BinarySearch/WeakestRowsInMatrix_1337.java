package src.BinarySearch;

import java.util.Arrays;

public class WeakestRowsInMatrix_1337 {
    /*
    You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
    The soldiers are positioned in front of the civilians.
    That is, all the 1's will appear to the left of all the 0's in each row.

A row i is weaker than a row j if one of the following is true:

The number of soldiers in row i is less than the number of soldiers in row j.
Both rows have the same number of soldiers and i < j.
Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.



Example 1:

Input: mat =
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]],
k = 3
Output: [2,0,3]
Explanation:
The number of soldiers in each row is:
- Row 0: 2
- Row 1: 4
- Row 2: 1
- Row 3: 2
- Row 4: 5
The rows ordered from weakest to strongest are [2,0,3,1,4].
Example 2:

Input: mat =
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]],
k = 2
Output: [0,2]
Explanation:
The number of soldiers in each row is:
- Row 0: 1
- Row 1: 4
- Row 2: 1
- Row 3: 1
The rows ordered from weakest to strongest are [0,2,3,1].


Constraints:

m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] is either 0 or 1.
    */

    public static void main(String[] args) {
        int[][] test = {{1, 0}, {1, 0}, {1, 0}, {1, 1}};
        int[] answer1 = kWeakestRows(test, 4);
        int[] answer2 = kWeakestRows(test, 3);

        for (int num : answer1) {
            System.out.print(num + ", ");
        }
        System.out.println();
        for (int num : answer2) {
            System.out.print(num + ", ");
        }
        System.out.println();

    }

    private static int[] kWeakestRows(int[][] mat, int k) {
        int height = mat.length;
        int[][] map = new int[height][2];

        for (int i = 0; i < height; i++) {
            map[i][0] = getStrength(mat[i]);
            map[i][1] = i;
        }

        Arrays.sort(map, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = map[i][1];
        }

        return result;
    }

    private static int getStrength(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == 1) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    /*
    关键的点是在如果运用矩阵来实现类似于map的功能, 此题中我们要对每row士兵的数量进行排序, 但是我们并不return 数量的array,而是row_i的array.
    这是最头痛的点, 如果使用TreeMap, 无法排序, 所以我们只能手搓int[][]矩阵来模拟map. 这是第一个关键的点, 第二则是如果sort的问题, 根据Java
    的语法, 我们可以用多种方式去sort 一个矩阵, sort逻辑是第一列按从小到大的顺序排, 如果相等,第二列从小到大. 这个技巧是需要牢牢掌握的.
    */

}
