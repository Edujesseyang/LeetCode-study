package src.SlidingWindow;

import java.util.Arrays;

public class LongestSubStringWithoutRepeat_3 {

    public static void main(String[] args) {
        String test = "abcdacbda";
        int answer = lengthOfLongestSubstring(test);
        System.out.println(answer);
    }

    /**
     * 此解法的重点再于
     * 1. 建立一个用于储存最后位置信息的array, 比用loop来一一检索窗口快的多
     * 2. loop里面的逻辑判断顺序不能乱:
     *         1) 先更新慢针位置, 因为要统计的是最靠近当前char的那个相同char直接的长度.
     *         2) 再统计快慢针之间的长度.
     *         3) 最后再把当前位置加入last位置map用于下一次的判断, 此顺序万不可乱, 祖宗之法!
     */
    private static int lengthOfLongestSubstring(String s) {
        char[] sArr = s.toCharArray(); // 先把string转成arr, 缩短检索的时间
        int[] lastPosition = new int[256]; // 建一个256的表格, 其index相当于ASCII的数字, 内容用于记录某字符上一次出现的位置信息.
        Arrays.fill(lastPosition, -1); // 初始化这个表格全部为-1

        int max = 0; // 建立答案var
        for (int i = 0, j = 0; i < sArr.length; i++) { // 从头loop到尾, i为快针, j为慢针
            j = Math.max(j, lastPosition[sArr[i]] + 1); // 更新j的位置, 看j的位置和上次出现的位置哪个更靠近i
            max = Math.max(max, i - j + 1); // 更新答案
            lastPosition[sArr[i]] = i; // 将快针的最后位置信息更新到表格.
        }
        return max;
    }
}
