package src.BinarySearch;

public class KthMissingPositiveNum_1539 {
    /*Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array.



Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.


Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length


Follow up:

Could you solve this problem in less than O(n) complexity?*/

    public static void main(String[] args) {
        int[] test1 = {2, 3, 4, 7};
        int[] test2 = {1, 2, 3, 4};

        System.out.println("Test 1 : Output: " + findKthMissingPos(test1, 5) + " Expected: 9");
        System.out.println("Test 1 : Output: " + findKthMissingPos(test2, 2) + " Expected: 6");
    }

    /*
    逻辑:
    这里我们用二分查找来做, 核心逻辑是首先找到缺失位置后面的那个数字, 然后这个数字的index等于是如果填满的话, 这个位置上应该有的数字.
    然后用这个应该有的数字往后数k位, 就是缺失的 kth 数字
    */

    private static int findKthMissingPos(int[] nums, int k) {
        int left = 0;
        int right = nums.length; // 关键1: 这里不能定死最右位, 因为有可能这个k位超过了最后一位

        while (left < right) { // 关键2: 和普通BS不同, 这里要逼left跑到right的右边.
            int mid = left + (right - left) / 2;

            int missedCount = nums[mid] - (mid + 1); // 关键3: 每一圈都要找到现在的mid的走手边少了多少数字

            if (missedCount < k) { // 如果缺失少于k, 则继续往右找
                left = mid + 1;
            } else {     // 如果缺失多于k, 则表示k位没超过最后一位, 这是
                right = mid;  // 我们重新设定有边界
            }
        }
        // 最后当跳出循环时, 保证left所在位置刚好时缺失位置的一个.
        return left + k; // 此时,该位置的index刚好时如果填满时这里应该放着的数, 加上k就得的了kth位置.
    }

    /*
    * 此题很抽象, 主要容易乱的几个点是:
    * 1. 位置和所在的数容易搞混.
    * 2. 我们BS的不是某一个数, 而是一个不存在的位置.
    * 3. 列一个连续的数列, 在上面做有助于理清思路.
    * */
}
