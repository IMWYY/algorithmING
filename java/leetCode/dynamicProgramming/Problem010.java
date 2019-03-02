package leetCode.dynamicProgramming;

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
     * 方法一
     * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
     * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
     * 3, If p.charAt(j) == '*':
     *    here are two sub conditions:
     *                1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
     *                2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
     *                               dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
     *                            or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        // 初始化的时候需要对dp[0]特殊考虑
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;  // .*结构
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];    // skip .*
                    } else {
                        dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }


    /**
     * 方法二
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
            return (firstMatch && match(s, i + 1, p, j)) || match(s, i, p, j + 2); //要么一起跳过 要么不跳过
        } else {
            return firstMatch && match(s, i + 1, p, j + 1);
        }
    }

    /**
     * 方法三
     * 动态规划 由方法二的递归转换而来
     */
    public boolean isMatch2(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first_match = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (first_match && dp[i + 1][j]);
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

}
