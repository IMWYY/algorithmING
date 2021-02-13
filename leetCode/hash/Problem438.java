package leetCode.hash;

import java.util.*;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both
 * strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 */
public class Problem438 {

    /**
     * 滑动窗口的思想
     * 当key是char的时候 可以利用char[]来代替hashMap 并且可以直接将char转换为string进行比较
     * O(n) time + O(n) space
     * a template for reference:
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
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

    public static void main(String[] args) {
        List<Integer> res = findAnagrams1("aabaa", "aa");
        System.out.println(res.size());
    }

    /**
     * 不使用char 直接用hashmap和count计数来实现滑动窗口的检测
     */
    public static List<Integer> findAnagrams1(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int start = 0, end = 0, count = 0;
        while (end < s.length()) {
            while (end < s.length() && end - start < p.length()) {
                if (map.containsKey(s.charAt(end))) {
                    map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
                    if (map.get(s.charAt(end)) == 0) {
                        count++;
                    }
                }
                end++;
            }

            if (count == map.size()) {
                result.add(start);
            }
            if (map.containsKey(s.charAt(start))) {
                if (map.get(s.charAt(start)) == 0) {
                    count--;
                }
                map.put(s.charAt(start), map.get(s.charAt(start)) + 1);
            }
            start++;

        }

        return result;
    }
}
