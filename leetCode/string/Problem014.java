package leetCode.string;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * All given inputs are in lowercase letters a-z.
 */
public class Problem014 {
    /**
     * we can also use trie
     * refer to https://leetcode.com/articles/implement-trie-prefix-tree/
     */

    // Horizontal scanning
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        String res = strs[0];
        for (int i = 1; i < strs.length; ++i) {
            if (res.equals("") || strs[i].equals("")) return "";
            int index = res.length();
            while (!strs[i].startsWith(res.substring(0, index))) {
                index--;
                if (index == 0) return "";
            }
            res = res.substring(0, index);
        }
        return res;
    }

    // Vertical scanning
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        for (int i = 0; i < strs[0].length(); ++i) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; ++j) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
