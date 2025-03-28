package src.Matrix;

public class CountNegativeNumInMatrix_1351 {
   /*
    Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.




            Example 1:

    Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
    Output: 8
    Explanation: There are 8 negatives number in the matrix.
            Example 2:

    Input: grid = [[3,2],[1,0]]
    Output: 0


    Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 100
    -100 <= grid[i][j] <= 100


    Follow up: Could you find an O(n + m) solution?
    */

    public static void main(String[] args) {
        int[][] test = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        System.out.println(countNegInMatrix(test));
    }

    private static int countNegInMatrix(int[][] grid) {
        // note down the size of the matrix
        int numOfRow = grid.length;
        int numOfCol = grid[0].length;

        // define row and col pointers
        int r = 0, c = numOfCol - 1;
        // define a returning result
        int sum = 0;

        // if row < numOfRow
        while (r < numOfRow) {
            // if the far right in each col is negative
            if (grid[r][c] < 0) {
                // move col pointer one left
                c--;
            } else {
                // if it is not negative, add the count all right side elements, they are all negative
                sum += numOfCol - c - 1;
                // update col pointer
                c = numOfCol - 1;
                // move to the next row
                r++;
            }

            // special case stopper:
            //  if the whole row is all negative, means that the following rows are all negative
            if (c < 0) {
                // add all the rest to sum
                sum += (numOfRow - r) * numOfCol;
                break;
            }
        }

        return sum;
    }

    /*
    关键点:
    1. 这一题有两种思路, 一个是从下往上加, 一个是从上往下加, 速度快慢会根据数据的分别决定.
        我这里使用从上往下加的思路主要因为, 想要最大化利用题目中说的row首项的排序为降序这一个特点,
        可以在首项小于零是将余下的整体打包加到结果中.
    2. 容易出错的点就是: 1) row 和 col 不要弄混. 2) 边界一定小心.
     */
}
