package src.BinarySearch;

public class SqrtOfX_69 {
    //    Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
//    The returned integer should be non-negative as well.
//
//    You must not use any built-in exponent function or operator.
//
//    For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
//
//
//    Example 1:
//
//    Input: x = 4
//    Output: 2
//    Explanation: The square root of 4 is 2, so we return 2.
//    Example 2:
//
//    Input: x = 8
//    Output: 2
//    Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
//
//
//    Constraints:
//
//            0 <= x <= 231 - 1
    public static void main(String[] args) {
        int[] tests = {2, 1, 0, 99, 888, 455556666, 8};
        for (int n : tests) {
            System.out.println("Test :" + n + " output: " + sqrtOf(n) + " expected: " + (int) Math.sqrt(n));
        }
    }

    private static int sqrtOf(int x) {
        // define two pointer num.
        int left = 0;
        int right = x;

        // logic:
        //  1. find a mid-point somewhere between left and right
        //  2. get a square of mid.
        //  3. compare square to input
        //  4. if square is greater than x, update left to mid + 1;
        //     if square is smaller than x, update right to mid - 1;
        //     if square == x, means mid * mid = x; return mid;
        //  5. the last loop mid should equal to left and right.
        //      so, the compare will be:
        //          if square is less than x, left + 1; the right points the correct answer.
        //          if square is greater than x, right + 1, the right now points the correct answer as well.
        //          because, square is too big. we need to increase the result.
        //   thus, we need to return right for both cases.
        while (left <= right) {
            // define 2 variables: mid is somewhere between left and right
            int mid = left + (right - left) / 2;
            // square is square of mid
            long square = (long) mid * mid;
            // compare:
            if (square == x) { // mid is sqrt(x)
                return mid;
            } else if (square < x) { // right half
                left = mid + 1;
            } else if (square > x) { // left half
                right = mid - 1;
            }

        }
        return right; // return the correct side
    }

    // 关键点:
    // 这里的二分查找是找从0-x的中的的后半段. 因为任何数的平方肯定大于它乘以1.5.
    // 所以mid取值为大概0-x的中间值的大概1.5倍更有效率. mid=(right - left) /2 也是可以的.
    // 但是, 因为当 left 和 right 都很大时，left + right 可能会整型溢出.
    // (left + right) / 2 就变成 4_100_000_000 / 2 = 溢出
    // 而 left + (right - left) / 2 不会溢出
    // 最佳取值是 mid = left * (right - left) / 2

}
