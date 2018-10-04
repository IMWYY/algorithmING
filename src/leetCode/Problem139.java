package leetCode;

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

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code2");
        System.out.println(new Problem139().wordBreak("leetcode", list));
    }

    /**
     * 动态规划：dp[i] 表示到i位置是否能够被wordDict组成
     * dp[i] = map.getOrDefault(s.substring(j, i + 1), false) && dp[j - 1];
     */
    @SuppressWarnings("all")
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty() || wordDict.isEmpty()) return false;

        boolean[] dp = new boolean[s.length()];
        Arrays.fill(dp, false);

        Map<String, Boolean> map = new HashMap<>();
        for (String s1 : wordDict) {
            map.put(s1, true);
        }
        dp[0] = map.getOrDefault(s.substring(0, 1), false);
        String temp;
        for (int i = 1; i < dp.length; ++i) {
            int j = 0;
            while (!dp[i] && j <= i) {
                temp = s.substring(j, i + 1);
                if (j == 0) {
                    dp[i] = map.getOrDefault(temp, false);
                } else {
                    dp[i] = map.getOrDefault(temp, false) && dp[j - 1];
                }
                j++;
            }
        }
        return dp[s.length() - 1];
    }
}
