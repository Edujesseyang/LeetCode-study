package src.SlidingWindow;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate2_219 {
    /*
    Given an integer array nums and an integer k,
    return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.



Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false


Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
0 <= k <= 105
    */

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 1};
        int[] test2 = {1, 0, 1, 1};
        int[] test3 = {1, 2, 3, 1, 2, 3};

        System.out.println("Test 1: Output: " + isContainDuplicate(test1, 3) + " Expected: true");
        System.out.println("Test 2: Output: " + isContainDuplicate(test2, 1) + " Expected: true");
        System.out.println("Test 3: Output: " + isContainDuplicate(test3, 2) + " Expected: false");
    }

    /**
     * 一个hashSet的解法, 核心就是利用set.add()会返回Boolean来显示set中是否存在这一个特性. 来进行判断,
     * 如果无法成功添加, 表示该element已经存在.
     * 所以loop的核心只需两步:
     * 1. 尝试添加set并判断是否成功, 如果不成功则直接return true.
     * 2. 保持窗口大小为k.
     */
    private static boolean isContainDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        for (int i = 0; i < nums.length; i++) { // 遍历数组
            if (i > k) { // 这一步是维护边界, 当i已经超过了k时
                window.remove(nums[i - k - 1]); // 将左边界踢一个：保持窗口长度最多为 k
            }
            if (!window.add(nums[i])) { // 添加新的进入窗口, 并判断.
                return true;
            }
            // 这一题将右边框的除了放在后面主要因为它根结算绑定在一起, 如果想先结算再维护窗口的话需要多使用一个var指向左边框, 稍费内存
            // 这里不是必须时候j的原因是, i和j本质上是同步增加的, 始终保持k, 所有无须另建变量.
        }
        return false;
    }
}
