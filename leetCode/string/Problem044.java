package leetCode.string;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 */
public class Problem044 {
    // solution 1
    // dynamic programming
    // O(mn) space + O(mn) time
    public boolean isMatch(String s, String p) {
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
     * solution2
     * The basic idea is to have one pointer for the string and one pointer for the pattern.
     * This algorithm iterates at most length(string) + length(pattern) times,
     * for each iteration, at least one pointer advance one step.
     * O(m+n) time + O(1) space
     * worst case O(MN) time
     * https://leetcode.com/problems/wildcard-matching/discuss/17810/Linear-runtime-and-constant-space-solution
     */
    public boolean isMatch1(String s, String p) {
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
}