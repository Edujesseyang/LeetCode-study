package src.DoublePointer;

public class FindTheArrayConcatValue_2562 {
    /*You are given a 0-indexed integer array nums.

The concatenation of two numbers is the number formed by concatenating their numerals.

For example, the concatenation of 15, 49 is 1549.
The concatenation value of nums is initially equal to 0. Perform this operation until nums becomes empty:

If nums has a size greater than one, add the value of the concatenation of the first and the last element
to the concatenation value of nums, and remove those two elements from nums. For example, if the nums was [1, 2, 4, 5, 6],
add 16 to the concatenation value.
If only one element exists in nums, add its value to the concatenation value of nums, then remove it.
Return the concatenation value of nums.



Example 1:

Input: nums = [7,52,2,4]
Output: 596
Explanation: Before performing any operation, nums is [7,52,2,4] and concatenation value is 0.
 - In the first operation:
We pick the first element, 7, and the last element, 4.
Their concatenation is 74, and we add it to the concatenation value, so it becomes equal to 74.
Then we delete them from nums, so nums becomes equal to [52,2].
 - In the second operation:
We pick the first element, 52, and the last element, 2.
Their concatenation is 522, and we add it to the concatenation value, so it becomes equal to 596.
Then we delete them from the nums, so nums becomes empty.
Since the concatenation value is 596 so the answer is 596.
Example 2:

Input: nums = [5,14,13,8,12]
Output: 673
Explanation: Before performing any operation, nums is [5,14,13,8,12] and concatenation value is 0.
 - In the first operation:
We pick the first element, 5, and the last element, 12.
Their concatenation is 512, and we add it to the concatenation value, so it becomes equal to 512.
Then we delete them from the nums, so nums becomes equal to [14,13,8].
 - In the second operation:
We pick the first element, 14, and the last element, 8.
Their concatenation is 148, and we add it to the concatenation value, so it becomes equal to 660.
Then we delete them from the nums, so nums becomes equal to [13].
 - In the third operation:
nums has only one element, so we pick 13 and add it to the concatenation value, so it becomes equal to 673.
Then we delete it from nums, so nums become empty.
Since the concatenation value is 673 so the answer is 673.


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 104*/


    public static void main(String[] args) {
        int[] test1 = {5, 14, 13, 8, 12};
        int[] test2 = {7, 52, 2, 4};

        System.out.println("Test 1: output : " + findArrayConcatValue(test1) + " expected: 673 ");
        System.out.println("Test 2: output : " + findArrayConcatValue(test2) + " expected: 596 ");

    }

    private static long findArrayConcatValue(int[] nums) {
        // define 2 pointer
        int left = 0;
        int right = nums.length - 1;

        long sum = 0; // define result
        while (left < right) { // don't use <=, because we need the middle num
            sum += concat(nums[left], nums[right]); // helper function
            left++;
            right--;
        }
        if (left == right) { // add the middle num if nums.length is odd.
            sum += nums[left];
        }

        return sum;
    }

    /**
     *
     * @param a (int), the left half of the result number
     * @param b (int), right half
     * @return (long), a number concatenation of a and b
     */
    private static long concat(int a, int b) {
        // find the correct power of ten for b.
        int temp = b;
        int powerOfTen = 1;
        while (temp > 0) {
            // when temp b /= 10;
            temp /= 10;
            // same time powerOfTen *= 10;
            powerOfTen *= 10;
        }
        // concat them
        return ((long) a * powerOfTen) + b;
    }

    // Note:
    // 1. Use left/right pointers.
    // 2. Remember to handle the middle num in the array.
}
