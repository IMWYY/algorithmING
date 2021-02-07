#include <unordered_map>
#include <vector>

/**
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 *
 * You can return the answer in any order.
 */
std::vector<int> twoSum(std::vector<int>& nums, int target) {
  std::unordered_map<int, int> memo;
  for (int i = 0; i < nums.size(); ++i) {
    if (memo.count(target - nums[i]) > 0) {
      return {memo[target - nums[i]], i};
    }
    memo[nums[i]] =
        i;  // note that we can only add nums[i] to map after checking
  }
  return {0, 0};
}