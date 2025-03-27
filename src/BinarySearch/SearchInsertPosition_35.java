package src.BinarySearch;

public class SearchInsertPosition_35 {
    /*
    Given a sorted array of distinct integers and a target value, return the index if the target is found. If not,
    return the index where it would be if it were inserted in order.
    ** You must write an algorithm with O(log n) runtime complexity. **



Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104
    */
    public static void main(String[] args) {

        int[] test = {1, 3, 5, 6};
        int[] targets = {2, 5, 7};
        int[] results = {2, 1, 4};

        for (int i = 0; i < targets.length; i++) {
            System.out.println("Test " + (i + 1) + ": output: " + findPosition(test, targets[i]) + " expected: " + results[i]);
        }

    }

    private static int findPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // 精妙之所在, 如果直接用二者相加除以二, 有可能导致溢出, 这里用相减不会溢出, 除二再加上原来的left位置也可以得到二者中间位置.
            int mid = left + (right - left) / 2;
            // 以上思路需要特别记忆. 对各种中间位置定位皆有极大益处.
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
    /*
    注意点:
    原题要求找到数字则返回该数字的index, 未找到则返回应该插入的index.
    所以本题在经典二分查找的基础上加了一点, 原本未找到返回-1 改为了返回应在的位置.
    这里 返回left是个精妙设计, 如果loop完成, 此时的left应该在right的右边+1的位置. 刚好就是应该插入的位置.
    我们来分析,
        第一种情况: target 大于最右边的数字, loop结束时right在末位数字处, left++后在末位的后一位, 正是该插入位置.
        第二种情况: target 小于最左边位置(哪怕最小的element为0), 这时loop结束时, right 在-1的位置, 而left始终为0,
                  此时应该插入array首位, 也就是left位置.
        第三种情况: target 位于左右之间,但不存在, 这时的设计正是最精妙之所在, 当最后一圈loop, mid比对时为小于目标,
                  这时right位置会被至小于left的位置并跳出循环, left则成了目标位置
                  反之,如果最后一圈loop, mid比对时大于目标, 更新后的left和right会处在相同的位置, 并跳出循环,
                  无论那种情况, left都会指向目标位置.

    * 为什么 left 是插入位置？
    在二分查找的标准实现中，当 left > right 时，循环结束。
    此时的 left 一定指向第一个大于等于目标值的位置。
    如果目标值存在，left 会指向它；如果不存在，left 就是目标值应该插入的位置。

    * 既然 left 最终指向插入位置，那 right + 1 是不是也可以？”
    答案是：不推荐直接返回 right + 1，原因如下：
    可读性差：left 本身就被设计为指向插入位置，用它返回更直观。
    不必要的复杂性：在不同场景下，right + 1 不一定是正确答案。
    鲁棒性弱：在边界条件下，left 始终有效，而 right + 1 可能带来额外判断。
     */

}
