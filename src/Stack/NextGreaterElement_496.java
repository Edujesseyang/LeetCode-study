package src.Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class NextGreaterElement_496 {
    /*
    The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
If there is no next greater element, then the answer for this query is -1.

Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.



Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.


Constraints:

1 <= nums1.length <= nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 104
All integers in nums1 and nums2 are unique.
All the integers of nums1 also appear in nums2.


Follow up: Could you find an O(nums1.length + nums2.length) solution?
    */

    public static void main(String[] args) {

        System.out.println(Arrays.toString(findNextGreaterElements(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}))); // [-1,3,-1]
        System.out.println(Arrays.toString(findNextGreaterElements(new int[]{2, 4}, new int[]{1, 2, 3, 4})));   // [3,-1]
        System.out.println(Arrays.toString(findNextGreaterElements(new int[]{2, 4}, new int[]{4, 3, 2, 1})));   // [-1,-1]
        System.out.println(Arrays.toString(findNextGreaterElements(new int[]{5}, new int[]{5})));           // [-1]
        System.out.println(Arrays.toString(findNextGreaterElements(new int[]{2}, new int[]{2, 2, 3})));       // [3]

    }

    /*
     解法思路:
     经典的单调栈维护递减数列的题.
     考虑做一个map来收集结果, HashMap或者int[] 映射都可以.
     遍历数组每遇到一个数字时, 如果stack里面有元素, 就把所有比当前元素小的一一pop出来, 这些小数的共有下一个比他们大的数就是当前元素.
     然后, 把当前元素压入栈.
     */
    private static int[] findNextGreaterElements(int[] targets, int[] sourceArr) {
        // 1. create a mapping array
        int[] map = new int[10001];
        Arrays.fill(map, -1);

        // 2. create a deque
        Deque<Integer> stack = new ArrayDeque<>();

        for (int num : sourceArr) {
            while (!stack.isEmpty() && stack.peek() < num) {
                /*
                key! the current num is the nextGreaterElement for
                all of those smaller nums who were in the stack.
                so, we need to pop all of them out
                and set their nextGreaterElement to the current num on the map.
                 */
                map[stack.pop()] = num;
            }
            stack.push(num);
        }

        // update target arr or make a new arr for returning
        int[] ans = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            ans[i] = map[targets[i]];
        }
        return ans;
    }
}
