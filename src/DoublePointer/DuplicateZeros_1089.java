package src.DoublePointer;

public class DuplicateZeros_1089 {
    /*
    Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.
Do the above modifications to the input array in place and do not return anything.



Example 1:

Input: arr = [1,0,2,3,0,4,5,0]
Output: [1,0,0,2,3,0,0,4]
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
Example 2:

Input: arr = [1,2,3]
Output: [1,2,3]
Explanation: After calling your function, the input array is modified to: [1,2,3]


Constraints:

1 <= arr.length <= 104
0 <= arr[i] <= 9
    */

    public static void main(String[] args) {
        int[] test = {1, 0, 2, 3, 0, 4, 5, 0};
        duplicateZeros(test);
        System.out.print("Output:   ");
        for (int num : test) {
            System.out.print(num + ", ");
        }
        System.out.println("\nExpected: 1, 0, 0, 2, 3, 0, 0, 4");

    }

    /**
     * 此题逻辑非常绕, 要考虑很多地方, 首先, 我们不能从前往后遍历, 以为会覆盖掉后面的数, 所以我们首先直觉从后往前.
     * 问题来了, 如何定左右指针是个难点. 如果考虑以右指针为基准指针, 我们无法准确判断左指针的位置, 以为如果arr最后几位有零会增加判断难度.
     * 所以我们要先将左指针准确定义为arr最后一位, 并且按照题目要求, 将右指针定义在左指针后zeroCount位, 这时右指针将出界.
     * 我们接下啦的循环需要在loop外逻辑上来模拟. 第一就是当right在界外时同时将左右都减减, 这时后检查如果左遇到了0, 则另外将右再减一下.
     * 直到右进入了边界, 我们正式开始我们的第一个循环, 只要将右改为左, 并在左遇见0时, 右向左进移动一下,并且将这个位置改为0, 这时就会连续出现两个0.
     * 直到left == right, 这就意味着前面没有0了, 结束循环.
     */
    private static void duplicateZeros(int[] arr) {
        int len = arr.length;
        // 1. 找到一共多少零出现在arr中
        int zeroCount = 0;
        for (int num : arr) {
            if (num == 0) {
                zeroCount++;
            }
        }

        // 2. 建两个指针, 一个left是在arr末尾, 一个right是在这个left理应所在的位置(可能超过arr长度)
        int left = len - 1;
        int right = len + zeroCount - 1; // 关键, 右指针建立在边界以外,一个抽象的位置.

        while (left < right) { // 左右相遇, 既表示前方再无零, 工作结束
            if (right < len) { // 当右进入边界, 循环主逻辑正式开始
                arr[right] = arr[left]; // 将左防止右的位置
            }
            if (arr[left] == 0) { // 每当左遇零, 做两件事.
                right--; // 第一缩进右, 一定先缩进
                if (right < len) { // 第二如果右此时在界内,
                    arr[right] = 0; // 右改0. 产生两零相邻
                }
            }
            left--; // 同缩左右.
            right--;
        }
    }
}
