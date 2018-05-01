package leetCode;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Example:
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * <p>
 * create by stephen on 2018/4/30
 */
public class Problem064 {

    /**
     * 典型动态规划解法 也可以直接在原数组上修改 这样可以节省空间
     */
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int[][] dp = new int[grid.length][grid[0].length];
        int temp = 0;
        for (int i = 0; i < dp[0].length; ++i) {
            temp += grid[0][i];
            dp[0][i] = temp;
        }

        temp = 0;
        for (int i = 0; i < dp.length; ++i) {
            temp += grid[i][0];
            dp[i][0] = temp;
        }

        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                dp[i][j] = (Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]);
            }
        }

        return dp[dp.length - 1][dp[0].length - 1];
    }

}
