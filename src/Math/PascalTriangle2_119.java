package src.Math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PascalTriangle2_119 {
    /*Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:




Example 1:

Input: rowIndex = 3
Output: [1,3,3,1]
Example 2:

Input: rowIndex = 0
Output: [1]
Example 3:

Input: rowIndex = 1
Output: [1,1]


Constraints:

0 <= rowIndex <= 33


Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?*/

    public static void main(String[] args) {
        List<Integer> result = getRow(5);

        for (Integer num : result) {
            System.out.print(num + ", ");
        }
        System.out.println("\n");

        List<Integer> result2 = getRowWithMath(6);
        for (Integer num : result2) {
            System.out.print(num + ", ");
        }
        System.out.println("\n");
    }

    /**
     * 方法1: 每行迭代, 只保存两行, 改行和前一行. TC=O(N^2), 精确计算是O(N * N/2)
     */
    private static List<Integer> getRow(int row) {
        int[] lastLvl = {1}; // 初始化第一行
        for (int i = 1; i < row + 1; i++) { // loop迭代到row的下一行
            int[] currentLvl = new int[i + 1]; // 创建当前行
            currentLvl[0] = 1; // 初始化首尾
            currentLvl[i] = 1;
            for (int j = 1; j <= i / 2; j++) { // 创建前一半的每一位, 后一半镜像前一半
                currentLvl[j] = lastLvl[j] + lastLvl[j - 1];
                currentLvl[i - j] = currentLvl[j];
            }
            lastLvl = currentLvl; // 设当前行为上一行
        }

        return Arrays.stream(lastLvl).boxed().collect(Collectors.toList());
    }

    /**
     * 方法2: 运用数学公式, 杨辉三角的每一层的每一个数都有公式可寻. 就是 C(row, k) = row! / k! * (row - k)! 这里row是层, k是位
     * 这里阶乘会导致数据溢出, 所以我们可以每层逐步累乘的方式来做, C(currentRow, k) = C(lastRow, k - 1) * (row - k + 1) / k; 来计算
     * 相当复杂, 没办法, 死记硬背, 但是知道方法1就挺好的.
     */
    private static List<Integer> getRowWithMath(int row) {
        List<Integer> result = new ArrayList<>(row + 1);
        result.add(1);
        long value = 1;
        for (int i = 1; i <= row; i++) {
            value = value * (row - i + 1) / i;
            result.add((int) value);
        }
        return result;
    }

}