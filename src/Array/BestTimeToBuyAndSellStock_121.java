package src.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BestTimeToBuyAndSellStock_121 {
    /*
    You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
    * */

    public static void main(String[] args) {
        int[] test1 = {7, 1, 5, 3, 6, 4};
        int[] test2 = {7, 6, 4, 3, 1};
        int[] test3 = {1, 1, 1, 1, 1};
        int[] test4 = {1, 2, 3, 4, 5, 6, 7};
        int[] test5 = {100, 1, 5, 3, 6, 101};

        List<int[]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3, test4, test5));
        int[] result = {5, 0, 0, 6, 100};

        for (int i = 0; i < result.length; i++) {
            System.out.println("Test " + (i + 1) + ": Output: " + maxProfit(tests.get(i)) + " expected: " + result[i]);
        }

    }

    /**
     * TC: O(n)
     * SC: O(1)
     *
     * @param prices : Unsorted int array
     * @return : max profit
     */

    private static int maxProfit(int[] prices) {
        // Filter off if null input and only 1 prices case
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // Define a pointer for lower point
        int lowPrice_i = 0;
        // Define a pointer for updating profit
        int profit = 0;

        // Iterate the prices and compare the currentPrice with low price
        for (int currentPrice_i = 0; currentPrice_i < prices.length; currentPrice_i++) {
            // If currentPrice if higher than lowPrice.
            if (prices[currentPrice_i] > prices[lowPrice_i]) {
                // Update the profit.
                profit = Math.max(profit, prices[currentPrice_i] - prices[lowPrice_i]);
            } else {
                // If not, update lowPrice
                lowPrice_i = currentPrice_i;
            }
        }

        return profit;
    }

    /*
     * 关键点:
     * 1. 此题可以用两个 for 循环套着暴力求解, 但是不推荐.
     * */
}
