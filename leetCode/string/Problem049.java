package leetCode.string;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 */
public class Problem049 {

    // O(n*klogk) time + O(n) space
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        String key;

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            key = String.valueOf(chars);
            if (map.get(key) == null) {
                List<String> strings = new ArrayList<>();
                strings.add(s);
                map.put(key, strings);
            } else {
                map.get(key).add(s);
            }
        }
        return new ArrayList<>(map.values());
    }
}