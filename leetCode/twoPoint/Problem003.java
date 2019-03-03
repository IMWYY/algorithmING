package leetCode.twoPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * create by stephen on 2018/11/3
 */
public class Problem003 {

    /**
     * if s[e] have a duplicate in the range [s, e) with index e′,
     * we don't need to increase ii little by little. We can skip all the elements in the range [s,e′]
     * and let i to be e′+1 directly.
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int end = 0, start = 0; end < n; end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(map.get(s.charAt(end)), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    /**
     * If we know that the charset is rather small, we can replace the
     * Map with an integer array as direct access table.
     * <p>
     * Commonly used tables are:
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     */
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int end = 0, start = 0; end < n; end++) {
            start = Math.max(index[s.charAt(end)], start);
            ans = Math.max(ans, end - start + 1);
            index[s.charAt(end)] = end + 1;
        }
        return ans;
    }

    /**
     * 两指针 + map 类似Problem076
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() < 2) return s.length();
        int len = 1, start = 0, end = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            int count = map.getOrDefault(s.charAt(end), 0);
            map.put(s.charAt(end++), count + 1);
            if (count > 0) {
                while (start < end) {
                    count = map.getOrDefault(s.charAt(start), 0);
                    map.put(s.charAt(start++), count - 1);
                    if (count > 1) break;
                }
            }
            len = Math.max(len, end - start);
        }
        return len;
    }
}
