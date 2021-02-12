#include <cmath>
#include <vector>

/**
 * negaive the element at index i if value i+1 exists.
 */
std::vector<int> findDuplicates(std::vector<int>& nums) {
  std::vector<int> res;
  for (int i = 0; i < nums.size(); ++i) {
    int idx = std::abs(nums[i]) - 1;
    if (nums[idx] < 0)
      res.push_back(idx + 1);
    else
      nums[idx] = -nums[idx];
  }
  return res;
}