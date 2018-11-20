package leetCode;

import java.util.Arrays;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * <p>
 * Example:
 * <p>
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * create by stephen on 2018/5/29
 */
public class Problem309 {

    /**
     * 利用有限自动机 有点难理解 参考：
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking)
     * <p>
     * 每一天只有三个操作，这三个操作导致状态的改变：
     * 1）买 2）卖 3）不做操作
     * s0该天不做操作 s1该天买入 s2 改天卖出
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int[] s1 = new int[prices.length];
        int[] s0 = new int[prices.length];
        int[] s2 = new int[prices.length];

        s0[0] = 0;
        s1[0] = -prices[0];         // 第一天就买入 亏钱
        s2[0] = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]);
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]);
            s2[i] = s1[i - 1] + prices[i];
        }
        return Math.max(s0[prices.length - 1], s2[prices.length - 1]);
    }

    /**
     * 优化后的dp 参考
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
     *
     * buy[i]  = max(rest[i-1]-price, buy[i-1])
     * sell[i] = max(buy[i-1]+price, sell[i-1])
     * rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
     * ====>
     * rest[i] = sell[i-1]
     * ====>
     * buy[i] = max(sell[i-2]-price, buy[i-1])
     * sell[i] = max(buy[i-1]+price, sell[i-1])
     */
    public int maxProfit1(int[] prices) {
        int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
        for (int price : prices) {
            prev_buy = buy;
            buy = Math.max(prev_sell - price, prev_buy);
            prev_sell = sell;
            sell = Math.max(prev_buy + price, prev_sell);
        }
        return sell;
    }

    /**
     * 暴力DP O(n3) time + O(n2) space
     */
    public int maxProfit2(int[] prices) {
        if (prices.length <= 1) return 0;
        if (prices.length == 2) return prices[1] > prices[0] ? prices[1] - prices[0] : 0;

        int[][] dp = new int[prices.length][prices.length];
        for (int i = 0; i < prices.length; ++i) {
            Arrays.fill(dp[i], 0);
        }
        int result = 0;
        for (int i = 0; i < prices.length - 1; ++i) {
            for (int j = i + 1; j < prices.length; ++j) {
                dp[i][j] = prices[j] - prices[i];
                for (int k = i + 1; k < j; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k]);
                    dp[i][j] = Math.max(dp[i][j], dp[k][j]);
                    if (k < j - 2) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + prices[j] - prices[k + 2]);
                    }
                }
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }
}
