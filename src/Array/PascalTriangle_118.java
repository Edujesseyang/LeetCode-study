package src.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PascalTriangle_118 {
    /*Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:




Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]


Constraints:

1 <= numRows <= 30*/

    public static void main(String[] args) {

        List<List<Integer>> result = generate(5);
        for (List<Integer> subList : result) {
            for (Integer i : subList) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("\n*****************\n");
        List<List<Integer>> result1 = generate(15);
        for (List<Integer> subList : result1) {
            for (Integer i : subList) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("\n*****************\n");
        List<List<Integer>> result2 = generate(0);
        for (List<Integer> subList : result2) {
            for (Integer i : subList) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("\n*****************\n");
    }

    private static List<List<Integer>> generate(int numRows) {
        // create a result list
        List<List<Integer>> result = new ArrayList<>();

        for (int lvlcount = 0; lvlcount < numRows; lvlcount++) {
            // create a level arraylist
            List<Integer> currentLvl = new ArrayList<>();
            // add the first 1
            currentLvl.add(1);
            // add the sum between each other from the previous level.
            for (int i = 1; i < lvlcount; i++) {
                currentLvl.add(result.get(lvlcount - 1).get(i - 1) + result.get(lvlcount - 1).get(i));
            }

            // if it is not the first level, add another 1 to the end.
            if (lvlcount != 0) {
                currentLvl.add(1);
            }

            // append the current level to the result;
            result.add(currentLvl);
        }
        return result;
    }

    /*
    关键点:
    1. 杨辉三角, 没啥注意点, 小心各个层的边界控制, 生成式的题, 特殊input case 不多, 注意边界控制即可. 用Arraylist 好过linkedList,
        因为有很多get(index) 查找.
     */

}
