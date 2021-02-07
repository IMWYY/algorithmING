#include <unordered_map>
#include <vector>

/**
 *
 * Given an integer array with all positive numbers and no duplicates, find the
 * number of possible combinations that add up to a positive integer target.
 */
int backtrack(std::unordered_map<int, int>&, std::vector<int>&, int);
int combinationSum4(std::vector<int>& nums, int target) {
  std::unordered_map<int, int> memo;
  return backtrack(memo, nums, target);
}

int backtrack(std::unordered_map<int, int>& memo, std::vector<int>& nums,
              int remain) {
  if (remain < 0) return 0;
  if (remain == 0) return 1;
  if (memo.count(remain) > 0) return memo[remain];
  int res = 0;
  for (int i = 0; i < nums.size(); ++i) {
    res += backtrack(memo, nums, remain - nums[i]);
  }
  memo[remain] = res;  // use memorization to avoid duplicate calculation
  return res;
}