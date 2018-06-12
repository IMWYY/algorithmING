package leetCode;


import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * create by stephen on 2018/6/2
 */
public class Problem322 {

    /**
     * 动态规划 完全背包问题变种
     * dp[i][j] 表示前i种coin组合成j的最少coin数量
     * dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i - 1][j])
     * <p>
     * 这里需要注意的是：转换方程中，如果前一个状态有0，不能计算在内，0会影响最终的结果
     * 如dp[i - 1][j]=0，则变成dp[i][j] = dp[i][j - coins[i]] + 1
     * 如dp[i][j - coins[i]]=0，则变成 dp[i][j] = dp[i - 1][j]
     * <p>
     * O(S*n) time + O(S*n) space
     */
    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins.length == 0) return -1;

        int[][] dp = new int[coins.length][amount + 1];
        for (int[] aDp : dp) {
            Arrays.fill(aDp, 0);
        }
        for (int j = 1; j < dp[0].length; ++j) {
            if ((j / coins[0]) * coins[0] == j) {
                dp[0][j] = j / coins[0];
            }
        }

        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                if ((j - coins[i] > 0 && dp[i][j - coins[i]] > 0) || j - coins[i] == 0) {
                    if (dp[i - 1][j] > 0) {
                        dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i][j - coins[i]] + 1;
                    }
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length - 1][amount] == 0 ? -1 : dp[coins.length - 1][amount];
    }


    /**
     * 简化版动态规划 O(S*n) time + O(S) space
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins.length == 0) return -1;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
