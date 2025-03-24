package src.DoublePointer;

import java.util.ArrayList;
import java.util.List;

public class RemoneElement_27 {
    /*Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
    The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted,
you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
The remaining elements of nums are not important as well as the size of nums.
Return k.
Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.



Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).


Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100*/
    public static void main(String[] args) {
        List<int[]> test = new ArrayList<>();
        test.add(new int[]{7, 1, 2, 2, 3, 3, 1, 5, 6, 8, 9, 6});
        test.add(new int[]{0, 6, 5, 3, 7, 3, 4, 6, 5, 5, 9, 7});
        test.add(new int[]{2, 7, 2, 2, 3, 3, 7, 5, 6, 7, 5, 8});
        test.add(new int[]{5, 5, 6, 6, 8, 4, 5, 3, 5, 3, 9, 7});
        test.add(new int[]{0, 1, 2, 2, 3, 3, 4, 5, 6, 8, 3, 7});


        System.out.print("Test 1: ");
        printTest(test.getFirst());
        System.out.print("Output:   ");
        printResult(test.getFirst(), removeElement(test.getFirst(), 2));
        System.out.println("Expected: {7, 1, 3, 3, 1, 5, 6, 8, 9, 6, }");

        System.out.print("\nTest 2: ");
        printTest(test.get(1));
        System.out.print("Output:   ");
        printResult(test.get(1), removeElement(test.get(1), 5));
        System.out.println("Expected: {0, 6, 3, 7, 3, 4, 6, 9, 7, }");

        System.out.print("\nTest 3: ");
        printTest(test.get(2));
        System.out.print("Output:   ");
        printResult(test.get(2), removeElement(test.get(2), 2));
        System.out.println("Expected: {7, 3, 3, 7, 5, 6, 7, 5, 8, }");

        System.out.print("\nTest 4: ");
        printTest(test.get(3));
        System.out.print("Output:   ");
        printResult(test.get(3), removeElement(test.get(3), 5));
        System.out.println("Expected: {6, 6, 8, 4, 3, 3, 9, 7, }");

        System.out.print("\nTest 5: ");
        printTest(test.get(4));
        System.out.print("Output:   ");
        printResult(test.get(4), removeElement(test.get(4), 7));
        System.out.println("Expected: {0, 1, 2, 2, 3, 3, 4, 5, 6, 8, 3, }");

    }

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        return i;
    }

    public static void printTest(int[] nums) {
        System.out.print("{");
        for (int i : nums) {
            System.out.print(i + ", ");
        }
        System.out.println("}");
    }

    public static void printResult(int[] nums, int k) {
        System.out.print("{");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + ", ");
        }
        System.out.println("}");
    }

    // 解法:
    /* 经典的双指针, 初始快慢针为零;
        快针近loop, 如果快针指向值非为指定, swap.
        然后跟新慢针, 再等下快针.
     */
}
