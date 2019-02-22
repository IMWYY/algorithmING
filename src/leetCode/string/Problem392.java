package leetCode;

import java.util.*;

/**
 * Given a string s and a string t, check if s is subsequence of t.
 * You may assume that there is only lower case English letters in both s and t.
 * t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * <p>
 * A subsequence of a string is a new string which is formed from the original string by
 * deleting some (can be none) of the characters without disturbing the relative positions of
 * the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 * <p>
 * Example 1:
 * s = "abc", t = "ahbgdc"
 * Return true.
 * <p>
 * Example 2:
 * s = "axc", t = "ahbgdc"
 * Return false.
 * <p>
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one
 * to see if T has its subsequence. In this scenario, how would you change your code?
 * create by stephen on 2019/2/22
 */
public class Problem392 {

    public static void main(String[] args) {
        Problem392 p = new Problem392();
        System.out.println(p.isSubsequence1("abc", "ahbgdc"));
        System.out.println(p.isSubsequence1("axc", "ahbgdc"));
        System.out.println(p.isSubsequence1("abb", "ahbbcc3"));
        System.out.println(p.isSubsequence1("leeeeecode", "alhebelelleooecode"));
    }

    /**
     * 对于s查找每一个字符 查找在t中第一次出现的位置
     * 如果是一个合法的子序列，那么所有的位置应该是单调严格递增的
     * O(nm) time + O(1) space
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int lastIndex = 0;
        for (char c : s.toCharArray()) {
            int newIndex = t.substring(lastIndex).indexOf(c);
            if (newIndex == -1) {
                return false;
            }
            lastIndex += newIndex + 1;
        }
        return true;
    }

    /**
     * 考虑follow up
     * 缓存所有的char->index的映射 查询的时候利用二分查找
     */
    public boolean isSubsequence1(String s, String t) {
        if (s.length() == 0) return true;

        List<Integer>[] map = new List[26];
        for (int i = 0; i < t.length(); ++i) {
            char c = t.charAt(i);
            if (map[c-'a'] != null) {
                map[c-'a'].add(i);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                map[c-'a'] = l;
            }
        }

        int lastIndex = -1;
        for (char c : s.toCharArray()) {
            if (map[c-'a'] == null) {
                return false;
            }
            List<Integer> list = map[c-'a'];
            // 利用二分查找 找到第一个大于等于lastIndex+1的index
            int i = Collections.binarySearch(list, lastIndex + 1);
            if (i >= 0) {
                lastIndex = list.get(i);
            } else {
                if (i == -list.size() - 1) {
                    return false;
                }
                lastIndex = list.get(-(i + 1));
            }
        }
        return true;
    }
}
