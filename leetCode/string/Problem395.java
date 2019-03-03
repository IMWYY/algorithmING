package leetCode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only)
 * such that every character in T appears no less than k times.
 * <p>
 * Example 1:
 * Input: s = "aaabb", k = 3
 * Output: 3
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * <p>
 * create by stephen on 2018/10/25
 */
public class Problem395 {

    /**
     * 递归算法
     */
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = new char[26];
        for (int i = 0; i < s.length(); i += 1) chars[s.charAt(i) - 'a']++;
        boolean flag = true;
        for (char aChar : chars) {
            if (aChar < k && aChar > 0) flag = false;
        }
        if (flag) return s.length();
        int result = 0;
        int start = 0, cur = 0;
        // we use all the infrequent elements as splits
        while (cur < s.length()) {
            if (chars[s.charAt(cur) - 'a'] < k) {
                result = Math.max(result, longestSubstring(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }
        result = Math.max(result, longestSubstring(s.substring(start), k)); // 防止出现最后一个字符没有计算到的情况 如k=3, s="aaa"
        return result;
    }


    /**
     * 利用two pointer来找到这个string
     * 这里的 i 是每次寻找 the number of unique characters in substring.
     */
    public int longestSubstring1(String s, int k) {
        int len = 0;
        for (int i = 0; i < 26; i++) {
            int[] cnts = new int[26];
            int begin = 0, end = 0, uniqueCtr = 0;
            while (end < s.length()) {
                boolean valid = true;
                if (cnts[s.charAt(end++) - 'a']++ == 0) uniqueCtr++;

                while (uniqueCtr > i)  //Make it valid
                    if (cnts[s.charAt(begin++) - 'a']-- == 1) uniqueCtr--;

                for (int j = 0; j < 26; j++) //If there is any character that has less than k repeating times. the string is invalid
                    if (cnts[j] > 0 && cnts[j] < k) {
                        valid = false;
                        break;
                    }

                if (valid) len = Math.max(len, end - begin);
            }
        }
        return len;
    }

    /**
     * 原始想法：没必要用map 可参考上面的递归算法
     * 想法是每次递归将小于k次的位置的char替换 然后对于每个被分割的子串递归
     */
    public int longestSubstring2(String s, int k) {
        if (s == null || s.length() < k) return 0;
        if (k == 1) return s.length();

        Map<Character, Pair> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            map.computeIfAbsent(c, k1 -> new Pair(c));
            map.get(c).indexs.add(i);
            map.get(c).times++;
        }
        char[] chars = s.toCharArray();
        boolean hasSmallLen = false;
        for (Map.Entry<Character, Pair> entry : map.entrySet()) {
            Pair pair = entry.getValue();
            if (pair.times < k) {
                hasSmallLen = true;
                for (int index : pair.indexs) {
                    chars[index] = 'a' - 1;
                }
            }
        }
        if (!hasSmallLen) return s.length();

        int res = 0, start = 0;
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] < 'a' || i == chars.length - 1) {
                if (i == chars.length - 1) i++;
                res = Math.max(res, longestSubstring(s.substring(start, i), k));
                start = i + 1;
            }
        }
        return res;
    }

    private class Pair {
        char c;
        int times;
        List<Integer> indexs;

        Pair(char c) {
            this.c = c;
            this.indexs = new ArrayList<>();
        }
    }

}
