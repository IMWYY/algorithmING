package leetCode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Find the length of the longest substring T of a given string (consists of lowercase letters only)
 * such that every character in T appears no less than k times.
 */
public class Problem395 {
    // solution1
    // split the string by less frequent chars and solve the problem on the substring
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = new char[26];
        for (int i = 0; i < s.length(); i += 1) chars[s.charAt(i) - 'a']++;
        // following checks are important, otherwise it will cause infinite recursion
        boolean flag = true;
        for (char aChar : chars) {
            if (aChar < k && aChar > 0) flag = false;
        }
        if (flag) return s.length();
        int result = 0;
        // we use all the infrequent elements as splits
        for (int start = 0, end = 0; end <= s.length(); end ++) {
            if (end == s.length()) {
                result = Math.max(result, longestSubstring(s.substring(start), k));
            } else if (chars[s.charAt(end) - 'a'] < k) {
                result = Math.max(result, longestSubstring(s.substring(start, end), k));
                start = end + 1;
            }
        }
        return result;
    }

    // solution2
    // we cannot apply the two-pointer template to the original problem, but
    // we can use it to solve a sub-problem.
    public int longestSubstring(String s, int k) {
        int res = 0;
        for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++)
            res = Math.max(d, longestSubstringWithNUniqueChars(s, k, numUniqueTarget));
        return res;
    }

    // find the longest substring with numUniqueTarget unique chars, and each char appears
    // at least k times.
    private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget) {
        int[] map = new int[128];
        int numUnique = 0;
        int numNoLessThanK = 0;
        int res = 0;
        for (int begin = 0, end = 0; end < s.length();) {
            if (map[s.charAt(end)]++ == 0) numUnique++; // increment map[c] after this statement
            if (map[s.charAt(end++)] == k) numNoLessThanK++; // inc end after this statement

            while (numUnique > numUniqueTarget) {
                if (map[s.charAt(begin)]-- == k) numNoLessThanK--; // decrement map[c] after this statement
                if (map[s.charAt(begin++)] == 0) numUnique--; // inc begin after this statement
            }
            // if we found a string where the number of unique chars equals our target
            // and all those chars are repeated at least K times then update max
            if (numUnique == numUniqueTarget && numUnique == numNoLessThanK)
                res = Math.max(end - begin, res);
        }
        return res;
    }
}
