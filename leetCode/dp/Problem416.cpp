#include <vector>
#include <numeric>

// 0/1 backpack problem
bool canPartition(std::vector<int>& nums) {
  int sum = std::accumulate(nums.begin(), nums.end(), 0);
  if ((sum & 1) == 1 || nums.size() == 1) return false;
  std::vector<std::vector<bool>> dp(nums.size() + 1,
                                    std::vector<bool>(sum / 2 + 1, false));

  for (int i = 0; i <= sum / 2; ++i) dp[0][i] = false;
  for (int i = 0; i <= nums.size(); ++i) dp[i][0] = true;

  for (int i = 1; i <= nums.size(); ++i) {
    for (int j = 1; j <= sum / 2; ++j) {
      dp[i][j] = (dp[i][j] || dp[i - 1][j]);
      if (j >= nums[i - 1]) dp[i][j] = (dp[i][j] || dp[i - 1][j - nums[i - 1]]);
    }
  }
  return dp[nums.size()][sum / 2];
}