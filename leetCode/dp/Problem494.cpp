#include <vector>

// 0/1 backpack problem
int findTargetSumWays(std::vector<int>& nums, int S) {
  int n = nums.size(), m = 2001;
  if (n == 0 || S < -1000 || S > 1000) return 0;  // handle corner case

  std::vector<std::vector<int>> dp(n, std::vector<int>(m, 0));
  dp[0][-nums[0] + 1000] += 1;
  dp[0][nums[0] + 1000] += 1;
  // note the nums[0]=0 case, +0 and -0 are two cases
  for (int i = 1; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      if (j - nums[i] >= 0 && j - nums[i] < m) {
        dp[i][j] += dp[i - 1][j - nums[i]];
      }
      if (j + nums[i] >= 0 && j + nums[i] < m) {
        dp[i][j] += dp[i - 1][j + nums[i]];
      }
    }
  }
  return ((S + 1000) >= 0 && (S + 1000) < m) ? dp[n - 1][S + 1000] : 0;
}