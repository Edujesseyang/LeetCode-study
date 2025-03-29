package src.BinarySearch;

import java.util.ArrayList;
import java.util.List;

public class MergeTwo2DArray_2570 {
    /*
    You are given two 2D integer arrays nums1 and nums2.

nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
Each array contains unique ids and is sorted in ascending order by id.

Merge the two arrays into one array that is sorted in ascending order by id, respecting the following conditions:

Only ids that appear in at least one of the two arrays should be included in the resulting array.
Each id should be included only once and its value should be the sum of the values of this id in the two arrays.
If the id does not exist in one of the two arrays, then assume its value in that array to be 0.
Return the resulting array. The returned array must be sorted in ascending order by id.



Example 1:

Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
Output: [[1,6],[2,3],[3,2],[4,6]]
Explanation: The resulting array contains the following:
- id = 1, the value of this id is 2 + 4 = 6.
- id = 2, the value of this id is 3.
- id = 3, the value of this id is 2.
- id = 4, the value of this id is 5 + 1 = 6.
Example 2:

Input: nums1 = [[2,4],[3,6],[5,5]], nums2 = [[1,3],[4,3]]
Output: [[1,3],[2,4],[3,6],[4,3],[5,5]]
Explanation: There are no common ids, so we just include each id with its value in the resulting list.


Constraints:

1 <= nums1.length, nums2.length <= 200
nums1[i].length == nums2[j].length == 2
1 <= idi, vali <= 1000
Both arrays contain unique ids.
Both arrays are in strictly ascending order by id.
    */

    public static void main(String[] args) {
        int[][] nums1 = {{1, 2}, {2, 3}, {4, 5}};
        int[][] nums2 = {{1, 4}, {3, 2}, {4, 1}};

        int[][] result = mergeTwo2DArray(nums1, nums2);
        System.out.print("{ ");
        for (int[] pair : result) {
            System.out.print ("[" + pair[0] + ", " + pair[1] + "], ");
        }
        System.out.println("}");
    }

    /**
     * O(m + n) : 每个element都会遍历一下, 最后 toArray 又会来一遍, 所以是 2(m+n)
     * @param nums1 : 2D array
     * @param nums2 : 2D array
     * @return : 2D array, merged 1 and 2
     */
    private static int[][] mergeTwo2DArray(int[][] nums1, int[][] nums2) {
        int i = 0;
        int j = 0;


        List<int[]> result = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i][0] == nums2[j][0]) {
                result.add(new int[]{nums1[i][0], nums1[i][1] + nums2[j][1]});
                i++;
                j++;
            } else if (nums1[i][0] < nums2[j][0]) {
                result.add(nums1[i]);
                i++;
            } else {
                result.add(nums2[j]);
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    //经典双指针merge, 每个指针控制一个array, 一个while loop 内做逻辑操作, 最后toArray一个放回需要的data type.
}
