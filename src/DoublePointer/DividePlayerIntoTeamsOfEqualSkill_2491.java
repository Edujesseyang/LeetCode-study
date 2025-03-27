package src.DoublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DividePlayerIntoTeamsOfEqualSkill_2491 {
    /*
    You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player.
Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.

The chemistry of a team is equal to the product of the skills of the players on that team.

Return the sum of the chemistry of all the teams,
or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.



Example 1:

Input: skill = [3,2,5,1,3,4]
Output: 22
Explanation:
Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
Example 2:

Input: skill = [3,4]
Output: 12
Explanation:
The two players form a team with a total skill of 7.
The chemistry of the team is 3 * 4 = 12.
Example 3:

Input: skill = [1,1,2,3]
Output: -1
Explanation:
There is no way to divide the players into teams such that the total skill of each team is equal.


Constraints:

2 <= skill.length <= 105
skill.length is even.
1 <= skill[i] <= 1000*/

    public static void main(String[] args) {
        int[] test1 = {3, 2, 5, 1, 3, 4};
        int[] test2 = {3, 4};
        int[] test3 = {1, 1, 2, 3};
        int[] test4 = {1, 2, 3, 4, 5, 6};

        List<int[]> tests = new ArrayList<>(Arrays.asList(test1, test2, test3, test4));
        long[] results = {22, 12, -1, 28};

        for (int i = 0; i < results.length; i++) {
            System.out.println("Test " + (i + 1) + ": Output: " + getSumOfChemistry(tests.get(i)) + " Expected: " + results[i]);
        }

    }

    private static long getSumOfChemistry(int[] skill) {
        Arrays.sort(skill);

        int left = 0;
        int right = skill.length - 1;
        int targetSkill = skill[left] + skill[right];
        long sumOfChemistry = 0;

        while (left < right) {
            if (skill[left] + skill[right] != targetSkill) {
                return -1;
            }
            sumOfChemistry += (long) skill[left] * skill[right];
            left++;
            right--;
        }

        return sumOfChemistry;
    }

    /*
    关键点:
    1. left和right是一对一配对, 不会出现那种一下跳过很多left或着right的情况, 所以可以一个循环一个循环的比对,
    不需要里面在加一个循环批量update left/right.
    2. sum 用long 因为该值会很大
    */
}
