package leetCode.dynamicProgramming;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * create by stephen on 2018/5/30
 */
public class Problem139 {

    /**
     * 动态规划：dp[i] 表示到i位置是否能够被wordDict组成
     * O(n^2) time + O(n) space
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && set.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[s.length()];
    }

    /**
     * DFS和BFS都可以解决这个问题 但是效率不如dp好
     * 这里给出BFS
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<String> set = new HashSet<>(wordDict);
        Set<Integer> visited = new HashSet<>();

        queue.add(0);
        while (queue.size() > 0) {
            int start = queue.poll();
            if (!visited.contains(start)) {
                visited.add(start);
                for (int j = start; j < s.length(); j++) {
                    String word = s.substring(start, j - start + 1);
                    if (set.contains(word)) {
                        queue.add(j + 1);
                        if (j + 1 == s.length())
                            return true;
                    }
                }
            }
        }
        return false;
    }
}
