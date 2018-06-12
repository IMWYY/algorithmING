package leetCode;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 * <p>
 * Example:
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * <p>
 * Note:
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 * create by stephen on 2018/5/12
 */
public class Problem049 {

    /**
     * 对于每个字符串字典序排序 或者 利用数组记录每个字符出现的次数 作为key
     * O(n*klogk) time + O(n) space
     */
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
