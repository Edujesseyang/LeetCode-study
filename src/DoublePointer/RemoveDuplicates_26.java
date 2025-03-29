package src.DoublePointer;

public class RemoveDuplicates_26 {
    /* Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
element appears only once. The relative order of the elements should be kept the same. Then return the number of
unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted; you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were
present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

Int k = removeDuplicates(nums); // Calls your implementation

Assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.



Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).


Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.*/

    public static void main(String[] args) {
        int[] test1 = {0, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7, 7};
        int[] test2 = {1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 6, 7, 7};
        int[] test3 = {0, 0, 0, 1, 1, 2, 3, 4, 4, 5, 5, 6, 7, 8};
        int[] test4 = {1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 5, 6, 7, 8};
        int[] test5 = {1, 2, 2, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9};

        int result1 = solution(test1);
        System.out.println(result1);
        int result2 = solution(test2);
        System.out.println(result2);
        int result3 = solution(test3);
        System.out.println(result3);
        int result4 = solution(test4);
        System.out.println(result4);
        int result5 = solution(test5);
        System.out.println(result5);


    }

    private static int solution(int[] nums) {
        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        System.out.print("{");
        for (int k = 0; k <= i; k++) {
            System.out.print(nums[k] + ", ");
        }
        System.out.println("}");
        return i + 1;

    }


}

/*
*  解法思路（双指针）
因为数组是升序排序的，所以重复元素一定是连续的，我们可以用两个指针：

i：指向下一个要填入唯一元素的位置
j：从前往后遍历整个数组

* */
