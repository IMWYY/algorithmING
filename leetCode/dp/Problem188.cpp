#include <climits>
#include <vector>

/**
 * You are given an integer array prices where prices[i] is the price of a given
 * stock on the ith day.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k
 * transactions.
 *
 * Notice that you may not engage in multiple transactions simultaneously (i.e.,
 * you must sell the stock before you buy again).
 *
 */

// optimized dp solution O(kn) time + O(n) space
int maxProfit(int k, std::vector<int>& prices) {
  int n = prices.size();
  if (n <= 1) return 0;
  std::vector<int> dp(n, 0);
  for (int l = 1; l <= k; ++l) {
    std::vector<int> tmp(dp);  // l-1
    int maxv = INT_MIN;
    for (int i = 1; i < n; ++i) {
      maxv = std::max(maxv, tmp[i - 1] - prices[i - 1]);
      dp[i] = std::max(dp[i - 1], maxv + prices[i]);
    }
  }
  return dp[n - 1];
}

// common dp solution o(kn^2) time + o(kn) space
// dp[k, i]: the maximum profit with at most k transaction in prices[0,i]
// dp[k,i] = max(dp[k, i-1], dp[k-1, j] + prices[i] - prices[j]) for j in 0,1..i-1
int maxProfit1(int k, std::vector<int>& prices) {
  int n = prices.size();
  if (n <= 1) return 0;
  std::vector<std::vector<int>> dp(k + 1, std::vector<int>(n, 0));
  for (int i = 1; i < n; ++i) {
    for (int l = 1; l <= k; ++l) {
      int maxv = 0;
      for (int j = 0; j < i; ++j) {
        maxv = std::max(maxv, dp[l - 1][j] + prices[i] - prices[j]);
      }
      dp[l][i] = std::max(dp[l][i - 1], maxv);
    }
  }
  return dp[k][n - 1];
}