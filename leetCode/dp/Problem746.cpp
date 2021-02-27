#include <vector>

// dynamic programming
int minCostClimbingStairs(std::vector<int>& cost) {
  int n = cost.size();
  std::vector<int> dp(n, 0);
  for (int i = 0; i < n; i++) {
    if (i < 2)
      dp[i] = cost[i];
    else
      dp[i] = cost[i] + std::min(dp[i - 1], dp[i - 2]);
  }
  return std::min(dp[n - 1], dp[n - 2]);
}