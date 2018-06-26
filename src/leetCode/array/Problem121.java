package leetCode.array;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction
 * (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * create by stephen on 2018/6/26
 */
public class Problem121 {

    /**
     * 找到最小值之后的最大值即可 用一个数字记录当前之前的最小值
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i=1; i<prices.length; ++i) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            maxProfit = Math.max(maxProfit, prices[i]-minPrice);
        }
        return maxProfit;
    }
}
