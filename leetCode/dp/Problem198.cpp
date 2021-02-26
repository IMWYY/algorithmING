#include <vector>

// can be optimized to O(1) space
int rob(std::vector<int>& nums) {
  if (nums.size() == 0) return 0;
  std::vector<int> dp(nums);
  int pre_max = 0, res = nums[0];
  for (int i = 1; i < dp.size(); ++i) {
    if (i > 1) pre_max = std::max(pre_max, dp[i - 2]);
    dp[i] += pre_max;
    res = std::max(res, dp[i]);
  }
  return res;
}