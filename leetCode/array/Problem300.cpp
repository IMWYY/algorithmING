#include <vector>

// solution1: O(nlogn) time + O(n) space
int lengthOfLIS(std::vector<int>& nums) {
  std::vector<int> res;  // res[i] keeps the minimum tail element of (i+1)
                         // length increasing subsequences
  for (int i = 0; i < nums.size(); ++i) {
    auto it = std::lower_bound(res.begin(), res.end(), nums[i]);
    if (it == res.end())
      res.push_back(nums[i]);
    else
      *it = nums[i];  // update it to a smaller one
  }
  return res.size();
}

// solution 2: dynamic programming
// dp[i] is the length of longest increasing subsequences ending at i
int lengthOfLIS(std::vector<int> nums) {
  if (nums.empty()) return 0;
  std::vector<int> dp(nums.size(), 1);
  int result = 1;
  for (int i = 1; i < nums.size(); i++) {
    for (int j = 0; j < i; j++) {
      if (nums[i] > nums[j]) {
        dp[i] = std::max(dp[j] + 1, dp[i]);
      }
    }
    result = std::max(result, dp[i]);
  }
  return result;
}