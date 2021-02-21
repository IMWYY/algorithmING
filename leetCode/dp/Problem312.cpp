#include <vector>

/**
 * top-down dynamic programming
 * find the last burst balloon first, looking up forward.
 * memo[i][j] is the max coin that we can get if we burst balloons
 * in the range (i, j).
 *
 * O(n^3) time + O(n^2) space
 */
int helper(std::vector<std::vector<int>>&, std::vector<int>&, int, int);
int maxCoins(std::vector<int>& nums) {
  std::vector<int> arr;
  arr.push_back(1);
  for (int i = 0; i < nums.size(); ++i) {
    if (nums[i] != 0) arr.push_back(nums[i]); // remove 0 first
  }
  arr.push_back(1);

  std::vector<std::vector<int>> memo(arr.size(), std::vector<int>(arr.size(), 0));
  return helper(memo, arr, 0, arr.size() - 1);
}

int helper(std::vector<std::vector<int>>& memo, std::vector<int>& arr, int l, int r) {
  if (l >= r) return 0;
  if (memo[l][r] > 0) return memo[l][r];
  int res = 0;
  for (int i = l + 1; i < r; ++i) {
    res = std::max(res, arr[i] * arr[l] * arr[r] +
                            helper(memo, arr, l, i) + helper(memo, arr, i, r));
  }
  memo[l][r] = res;
  return res;
}