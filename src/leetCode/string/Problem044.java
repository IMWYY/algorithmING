package leetCode.string;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * <p>
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * <p>
 * create by stephen on 2018/9/27
 */
public class Problem044 {

    public static void main(String[] args) {
        System.out.println(new Problem044().isMatch("abbbbbbbaabbabaabaa", "*****a*ab"));
    }

    /**
     * The basic idea is to have one pointer for the string and one pointer for the pattern.
     * This algorithm iterates at most length(string) + length(pattern) times,
     * for each iteration, at least one pointer advance one step.
     * O(m+n) time + O(1) space
     * <p>
     * https://leetcode.com/problems/wildcard-matching/discuss/17810/Linear-runtime-and-constant-space-solution
     */
    public boolean isMatch(String s, String p) {
        int sIndex = 0, pIndex = 0, match = 0, starIdx = -1;
        while (sIndex < s.length()) {
            // advancing both pointers
            if (pIndex < p.length() && (p.charAt(pIndex) == '?' || s.charAt(sIndex) == p.charAt(pIndex))) {
                sIndex++;
                pIndex++;
            }
            // * found, only advancing pattern pointer
            else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
                starIdx = pIndex;
                match = sIndex;
                pIndex++;
            }
            // 一旦遇到不匹配 pindex就回到*的下一个index
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1) {
                pIndex = starIdx + 1;
                match++;
                sIndex = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (pIndex < p.length() && p.charAt(pIndex) == '*')
            pIndex++;

        return pIndex == p.length();
    }


    /**
     * 递归转换为动态规划
     * O(mn) space + O(mn) time
     */
    public boolean isMatch1(String s, String p) {
        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[s.length()][p.length()] = true;
        // check all the tailing *
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) != '*')
                break;
            else
                match[s.length()][i] = true;
        }
        // check condition
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                    match[i][j] = match[i + 1][j + 1];
                else if (p.charAt(j) == '*')
                    match[i][j] = match[i + 1][j] || match[i][j + 1] || match[i + 1][j + 1];
                else
                    match[i][j] = false;
            }
        }
        return match[0][0];
    }

    /**
     * Time Limit Exceeded
     * 思路清晰 递归匹配 需要注意s="xxx",p="xxx*"的情况 需要特殊处理
     */
    public boolean isMatch2(String s, String p) {
        if (p.equals("")) return s.equals("");
        return isMatch(s, 0, p, 0);
    }

    public boolean isMatch(String s, int sIndex, String p, int pIndex) {
        if (sIndex == s.length() && pIndex == p.length()) return true;
        if (pIndex >= p.length() && sIndex < s.length()) return false;
        if (sIndex >= s.length()) {   // special here
            for (int i = pIndex; i < p.length(); ++i) {
                if (p.charAt(i) != '*') return false;
            }
            return true;
        }
        char c1 = s.charAt(sIndex), c2 = p.charAt(pIndex);
        if (c1 == c2 || c2 == '?') {
            return isMatch(s, sIndex + 1, p, pIndex + 1);
        }
        if (c2 == '*') {
            return isMatch(s, sIndex, p, pIndex + 1) ||
                    isMatch(s, sIndex + 1, p, pIndex) ||
                    isMatch(s, sIndex + 1, p, pIndex + 1);
        }
        return false;
    }
}
