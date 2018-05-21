package leetCode;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * <p>
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * <p>
 * Example 2:
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * <p>
 * create by stephen on 2018/5/21
 */
public class Problem010 {



    /**
     * 动态规划
     */
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true;
        for (int i = 1; i < s.length() + 1; ++i) {
            dp[i][0] = false;
        }
        for (int j = 1; j < p.length() + 1; j++) {
            if (j == 1) {
                dp[0][1] = false;
            } else {
                dp[0][j] = p.charAt(j-1) == '*' && dp[0][j - 2];
            }
        }

        for (int i = 1; i < s.length() + 1; ++i) {
            for (int j = 1; j < p.length() + 1; ++j) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*'){
                    // 遇到*则两种情况：p后退两步 或者 s后退一步
                    dp[i][j] = dp[i][j-2] || ((s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') && dp[i-1][j]);
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    /**
     * 递归解法 注意这里.* 结构要么一起跳过 要么不跳过
     * 可以先从没有*开始考虑，然后加入*
     */
    public boolean isMatch1(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        return match(s, 0, p, 0);
    }

    private boolean match(String s, int i, String p, int j) {
        if (j == p.length()) return i == s.length();
        boolean firstMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

        if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
            return (firstMatch && match(s, i + 1, p, j)) || match(s, i, p, j + 2);
        } else {
            return firstMatch && match(s, i + 1, p, j + 1);
        }
    }
}
