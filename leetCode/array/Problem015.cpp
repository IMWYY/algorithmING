#include <algorithm>
#include <vector>

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 *
 * Notice that the solution set must not contain duplicate triplets
 */
std::vector<std::vector<int>> threeSum(std::vector<int>& nums) {
  std::sort(nums.begin(), nums.end());
  std::vector<std::vector<int>> res;
  for (int i = 0; i < nums.size();) {
    int v = -nums[i];
    int start = i + 1, end = nums.size() - 1;
    while (start < end) {
      int l = nums[start], r = nums[end];
      if (l + r == v) {
        res.push_back({-v, l, r});
        while (nums[start] == l && start < end) start++;
        while (nums[end] == r && start < end) end--;
      } else if (l + r < v) {
        start++;
      } else {
        end--;
      }
    }
    i++;
    while (i < nums.size() && nums[i] == nums[i - 1]) i++;
  }
  return res;
}