#include <climits>
#include <vector>

/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you
 * must sell the stock before you buy again).
 *
 */
int maxProfit(std::vector<int>& prices) {
  if (prices.size() <= 1) return 0;
  std::vector<int> leftpro(prices.size(), 0), rightpro(prices.size(), 0);
  int minp = INT_MAX;
  for (int i = 0; i < prices.size(); ++i) {
    minp = std::min(minp, prices[i]);
    leftpro[i] = std::max(i == 0 ? 0 : leftpro[i - 1], prices[i] - minp);
  }
  int maxp = INT_MIN;
  for (int i = prices.size() - 1; i >= 0; i--) {
    maxp = std::max(maxp, prices[i]);
    rightpro[i] = std::max(i == 0 ? 0 : rightpro[i - 1], maxp - prices[i]);
  }
  int res = 0;
  for (int i = 0; i < prices.size(); ++i) {
    res = std::max(res, leftpro[i] + rightpro[i]);
  }
  return res;
}