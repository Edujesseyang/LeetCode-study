package src.DoublePointer;

public class ContainerWithMostWater_11 {
    /*
    You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.



Example 1:


Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
Example 2:

Input: height = [1,1]
Output: 1


Constraints:

n == height.length
2 <= n <= 105
0 <= height[i] <= 104
    */

    public static void main(String[] args) {

    }

    // 经典的双指针贪心, 左右指针, 维护一个临时的volume变量, 因为容积根据左右的较短的边决定, 所以更新较短的边.
    private static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxVol = 0;
        while (left < right) {
            int vol = (right - left) * Math.min(height[left], height[right]);
            maxVol = Math.max(maxVol, vol);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxVol;
    }
}
