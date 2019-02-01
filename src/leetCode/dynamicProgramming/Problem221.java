package leetCode.dynamicProgramming;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * Output: 4
 * create by stephen on 2018/5/31
 */
public class Problem221 {

    /**
     * 动态规划算法 dp[i][j] 表示正方形右下角坐标为(i,j)
     * 这里构造动态规划的转换表发现仍可以继续优化，只用一维数组
     * O(mn) time + O(nm) space （ or O (n) space）
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    // 注意这里 限制(i,j)位置边长的它之前三个点的最小值
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    /**
     * 动态规划 优化到O(n) space
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
                    dp[j] = 0;  //注意这里需要将dp[j] 重置为0
                }
                pre = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}
