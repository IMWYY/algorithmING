package leetCode.dynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 */
public class Problem221 {

    /**
     * dp[i][j] represent the side length of the square whose bottom-right position is (i,j)
     * O(mn) time + O(nm) space （ or O (n) space）
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    /**
     * optimize the dp solution to o(n) space
     */
    public int maximalSquare1(char[][] matrix) {
        int cols = matrix.length > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, pre = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), pre) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;  // reset dp[j] to 0
                }
                pre = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}
