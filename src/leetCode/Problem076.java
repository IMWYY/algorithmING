package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * <p>
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 * <p>
 * create by stephen on 2018/5/13
 */
public class Problem076 {

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); ++i) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int start = 0, end = 0, count = map.size();
        int len = Integer.MAX_VALUE, head = -1;

        while (end < s.length()) {
            char c = s.charAt(end++);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) count--;
            }

            while (count == 0) {
                if (len > end - start) {
                    len = end - start;
                    head = start;
                }
                c = s.charAt(start++);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                    if (map.get(c) > 0) count++;
                }
            }
        }

        if (head >= 0) {
            return s.substring(head, head + len);
        } else {
            return "";
        }
    }

}
