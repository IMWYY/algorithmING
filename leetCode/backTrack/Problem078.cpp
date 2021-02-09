
#include <vector>

/**
 * Given an integer array nums of unique elements, return all possible subsets
 * (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in
 * any order.
 *
 */
void backtrack(std::vector<std::vector<int>>&, std::vector<int>&, int,
               std::vector<int>&);
std::vector<std::vector<int>> subsets(std::vector<int>& nums) {
  std::vector<std::vector<int>> result;
  std::vector<int> current;
  backtrack(result, nums, 0, current);
  return result;
}

void backtrack(std::vector<std::vector<int>>& result, std::vector<int>& nums,
               int idx, std::vector<int>& current) {
  result.push_back(current);
  for (int i = idx; i < nums.size(); ++i) {
    current.push_back(nums[i]);
    backtrack(result, nums, i + 1, current);
    current.pop_back();
  }
}
