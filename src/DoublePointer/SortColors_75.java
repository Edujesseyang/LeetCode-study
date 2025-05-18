package src.DoublePointer;

public class SortColors_75 {
    /*
    Given an array nums with n objects colored red, white, or blue,
    sort them in-place so that objects of the same color are adjacent,
    with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.


Follow up: Could you come up with a one-pass algorithm using only constant extra space?
    */

    public static void main(String[] args) {

    }


    // 我自己的闭门造车写法, 只处理红色和蓝色, 放任白色, 通过处理蓝色时缩减i和len的方法来避免跳过白色.
    private static void sortColors(int[] nums) {
        for (int white = 0, red = 0, blue = nums.length - 1; white <= blue; white++) {
            if (nums[white] == 0) {
                swap(nums, red++, white);
            } else if (nums[white] == 2) {
                swap(nums, blue--, white--);
            }
        }
    }

    // GTP的优雅写法, 用三指针的逻辑.
    private static void sortColors2(int[] nums) {
        int red = 0, white = 0, blue = nums.length - 1;
        while (white <= blue) {
            if (nums[white] == 0) {
                swap(nums, red++, white++);
            } else if (nums[white] == 1) {
                white++;
            } else if (nums[white] == blue) {
                swap(nums, blue--, white);
            }
        }
    }
    // helper function
    private static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
