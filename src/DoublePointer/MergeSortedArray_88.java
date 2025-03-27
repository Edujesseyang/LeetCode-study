package src.DoublePointer;

public class MergeSortedArray_88 {
    /*You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
    representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.



Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109


Follow up: Can you come up with an algorithm that runs in O(m + n) time?*/

    public static void main(String[] args) {

        int[] test1 = {7, 8, 9, 9, 10, 99239, -936, 93456, -993569, 9236};
        int[] test2 = {-1, 0, 3, 5, 7};

        mergeArray(test1, test2, 5, 5);
        for (int i : test1) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    /**
     * merge arr2 into arr1, cover all elements after m's position
     * @param arr1 : int[] sorted array
     * @param arr2 : int[] sorted array
     * @param m : valid data length of arr1
     * @param n : valid data length of arr2
     */
    private static void mergeArray(int[] arr1, int[] arr2, int m, int n) {
        // we want to add the nums from arr2 to arr1 for the back
        // so, we define two pointers for last nums in both of arrays
        int i = m - 1;  // points last num in arr1
        int j = n - 1;  // points last num in arr2
        int k = m + n - 1; // the moving pointer initially to the correct last position of the result arr

        // if i and j are not -1. get into the loop
        while (i >= 0 && j >= 0) {
            // each loop, we compare the nums on i and j position, put the larger one into the k position.
            arr1[k--] = (arr1[i] > arr2[j]) ? arr1[i--] : arr2[j--];  // then decrease moved index and k
        }

        // if there is left over in arr2, keep add all of them into arr1.
        while (j >= 0 && k >= 0) {
            arr1[k--] = arr2[j--];
        }
    }

    /*
    精妙之所在:
    1. 运用给定的长度 m 和 n 来确定最终 arr 的长度.
    2. 用一个连续移动针不停的将两个 arr 的最后一位中大的哪一方填入移动针处. 并且更新移动后的 m 或 n.
    3. 当任意一个 arr 用完之后, 如 arr2 先用完, 表面工作完成. 如 arr1 先用完, 表示 arr2 有剩余, 那么就继续把 arr2 的剩余部分填入 arr1.
        直到 arr2 用完, 或者移动指针 k < 0.  两者都可以作为结束项, 也可以同时使用, 以防万一. 注意这里的 k 作为移动项在第一个 loop 跑完之后
        不用更新, 可以继续使用.
     */
}
