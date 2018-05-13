package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both
 * strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * <p>
 * Example 1:
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * <p>
 * Example 2:
 * Input:
 * s: "abab" p: "ab"
 * Output:
 * [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 * <p>
 * create by stephen on 2018/5/13
 */
public class Problem438 {

    /**
     * 滑动窗口的思想
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        char[] chars = new char[26];
        Arrays.fill(chars, '0');

        for (int i = 0; i < p.length(); ++i)
            chars[p.charAt(i) - 'a']++;

        String key = String.valueOf(chars);
        Arrays.fill(chars, '0');

        int start = 0, end = 0;

        while (end < s.length()) {
            while (end < s.length() && end - start < p.length())
                chars[s.charAt(end++) - 'a']++;
            if (end - start < p.length()) break;
            if (key.equals(String.valueOf(chars))) result.add(start);
            chars[s.charAt(start++) - 'a']--;
        }

        return result;
    }
}
