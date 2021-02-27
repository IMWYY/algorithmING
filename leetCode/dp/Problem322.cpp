#include <climits>
#include <vector>

// backpack problem
int coinChange(std::vector<int>& coins, int amount) {
  if (amount == 0) return 0;
  std::vector<std::vector<int>> dp(coins.size(),
                                   std::vector<int>(amount + 1, INT_MAX));
  for (int i = 0; i < coins.size(); ++i) {
    for (int j = 1; j <= amount; ++j) {
      if (j == coins[i]) dp[i][j] = 1;
      if (i > 0) dp[i][j] = std::min(dp[i][j], dp[i - 1][j]);
      if (j > coins[i] && dp[i][j - coins[i]] != INT_MAX)
        dp[i][j] = std::min(dp[i][j], dp[i][j - coins[i]] + 1);
    }
  }
  return dp[coins.size() - 1][amount] == INT_MAX ? -1
                                                 : dp[coins.size() - 1][amount];
}

// cleaner version: dynamic programming
int coinChange2(std::vector<int>& coins, int amount) {
  int Max = amount + 1;
  std::vector<int> dp(amount + 1, Max);
  dp[0] = 0;
  for (int i = 1; i <= amount; i++) {
    for (int j = 0; j < coins.size(); j++) {
      if (coins[j] <= i) {
        dp[i] = std::min(dp[i], dp[i - coins[j]] + 1);
      }
    }
  }
  return dp[amount] > amount ? -1 : dp[amount];
}