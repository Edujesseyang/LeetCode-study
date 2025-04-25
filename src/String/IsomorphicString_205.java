package src.String;

import java.util.HashMap;
import java.util.Map;

class IsomorphicString_205 {
    /*
    Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character, but a character may map to itself.



Example 1:

Input: s = "egg", t = "add"

Output: true

Explanation:

The strings s and t can be made identical by:

Mapping 'e' to 'a'.
Mapping 'g' to 'd'.
Example 2:

Input: s = "foo", t = "bar"

Output: false

Explanation:

The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.

Example 3:

Input: s = "paper", t = "title"

Output: true



Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.
    */

    public static void main(String[] args) {
/*
    方法	        核心思想	                            时间复杂度	    空间复杂度	       特点与优化建议
    approach1	双 HashMap，双向映射 s→t 和 t→s	    O(n)	        O(n)	           最安全、直观，避免 containsValue，适合常规
    approach2	单 HashMap + containsValue 反查	    O(n²)（最坏情况）	O(n)	           简单但效率低，containsValue 是瓶颈
    approach3	approach2 的小优化，预转数组	        O(n²)（最坏情况）	O(n)	           只是略省字符访问时间，复杂度本质没变
    approach4	数组映射，256 个字符 ASCII 范围内的优化	O(n)	        O(1) 固定空间	   最优方案，速度快，适合字符处理，需注意细节
*/


        System.out.println("(Approach 1) Test  egg, ill  :  " + approach1("egg", "ill"));
        System.out.println("(Approach 2) Test  egg, ill  :  " + approach2("egg", "ill"));
        System.out.println("(Approach 3) Test  egg, ill  :  " + approach3("egg", "ill"));
        System.out.println("(Approach 4) Test  egg, ill  :  " + approach4("egg", "ill"));

        System.out.println("(Approach 1) Test  piggy, cream  :  " + approach1("piggy", "cream "));
        System.out.println("(Approach 2) Test  piggy, cream   :  " + approach2("piggy", "cream "));
        System.out.println("(Approach 3) Test  piggy, cream   :  " + approach3("piggy", "cream "));
        System.out.println("(Approach 4) Test  piggy, cream   :  " + approach4("piggy", "cream "));

    }

    /**
     * 最稳定解放.
     * 双hashMap映射, 一个用于映射 s 到 t, 一个用于映射 t 到 s. 分别对比两个方向.
     * 好处是可以避免使用 map.containsValue(), 因为他的时间复杂度是 O(n).
     */
    private static boolean approach1(String s, String t) {
        Map<Character, Character> s_t = new HashMap<>();
        Map<Character, Character> t_s = new HashMap<>();

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        for (int i = 0; i < sArr.length; i++) {

            if (s_t.containsKey(sArr[i])) {
                if (s_t.get(sArr[i]) != tArr[i]) return false;
            } else {
                s_t.put(sArr[i], tArr[i]);
            }

            if (t_s.containsKey(tArr[i])) {
                if (t_s.get(tArr[i]) != sArr[i]) return false;
            } else {
                t_s.put(tArr[i], sArr[i]);
            }
        }

        return true;
    }

    /**
     * 但hashMap 映射, 一个 string 的 char 作为 key, 另一个作为 value.
     * 好处是不用使用第二个hashMap, 可以节省一些空间,
     * 但是坏处是使用了 map.containsValue(), 这个方法的时间复杂度为 O(n).
     * 极端情况下会使整体时间复杂度降低为 O(n^2).
     */
    private static boolean approach2(String s, String t) {
        Map<Character, Character> compare = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (compare.containsKey(s.charAt(i))) {
                if (compare.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else {
                if (compare.containsValue(t.charAt(i))) {
                    return false;
                }
                compare.put(s.charAt(i), t.charAt(i));
            }

        }
        return true;
    }

    /**
     * 上一个解法的优化版本, 事先将两个string转化为array, 降低了查找的时间需求.
     */
    private static boolean approach3(String s, String t) {
        Map<Character, Character> compare = new HashMap<>();
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();

        for (int i = 0; i < sa.length; i++) {
            if (compare.containsKey(sa[i])) {
                if (compare.get(sa[i]) != ta[i]) {
                    return false;
                }
            } else {
                if (compare.containsValue(ta[i])) {
                    return false;
                }
                compare.put(sa[i], ta[i]);
            }
        }
        return true;
    }

    /**
     * 数组映射法, 利用两个固定大小的int数组, 该方法仅限小字符表(ASCII), 大字符表(UniCode)  需要 int[65537] 才够.
     * 第一个map用s的char作为index, t的char作为value.
     * 第二个map用t的char作为index, s的char作为value.
     * 遍历两个charArray. 分别检查当前的这两个char 在各自map中的对于值是否等于对方,
     * 如果不等于则表示两个char其中一个已经出现过, 并且对应的是其他的char. 同时检查该位置是否为零, 如是, 则表示该char没有出现过.
     * 然后分别将两个char加入各自的map, 并且将另外一个设置为值. 注意这里每个作为值的char都加了1, 因为要规避出现某个char == 0 的情况.
     * 因为我们的map 初始值为零, 也可以事先fill整个map 为 -1. 这里为了提高速度没有采取这种方法.
     */
    private static boolean approach4(String s, String t) {
        int[] sMap = new int[257]; // 设置为257, 因为0 不参与判断是否相同, 只作为初始值
        int[] tMap = new int[257];

        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();

        for (int i = 0; i < sa.length; i++) {
            if (sMap[sa[i]] != 0 && sMap[sa[i]] != ta[i] + 1) { // 注意这里 +1, 为了规避char为0的状况
                return false;
            }
            if (tMap[ta[i]] != 0 && tMap[ta[i]] != sa[i] + 1) {
                return false;
            }
            sMap[sa[i]] = ta[i] + 1; // 这里同样需要 +1 保持一致.
            tMap[ta[i]] = sa[i] + 1;

        }
        return true;
    }
}
