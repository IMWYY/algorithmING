#include <climits>
#include <vector>

// solve it by state machine
// can be optimized to O(1) space
int maxProfit(std::vector<int>& prices) {
  if (prices.size() <= 1) return 0;
  // the maximum profit when we sell at ith day
  std::vector<int> sell(prices.size(), 0);
  // the maximum profit when we rest at ith day
  std::vector<int> rest(prices.size(), 0);
  // the maximum profit when we buy stock at ith day
  std::vector<int> buy(prices.size(), 0);
  // the maximum profit when we hold stock at ith day
  std::vector<int> hold(prices.size(), 0);
  buy[0] = -prices[0];
  hold[0] = buy[0];
  for (int i = 1; i < prices.size(); ++i) {
    buy[i] = rest[i - 1] - prices[i];
    sell[i] = hold[i - 1] + prices[i];
    rest[i] = std::max(rest[i - 1], sell[i - 1]);
    hold[i] = std::max(hold[i - 1], buy[i]);
  }
  return std::max(sell.back(), rest.back());
}
