#include <vector>
#include <cmath>

/**
 * negative the value at index i if value i+1 exists.
 **/
std::vector<int> findDisappearedNumbers(std::vector<int>& nums) {
  int len = nums.size();
  for (int i = 0; i < len; i++) {
    int m = std::abs(nums[i]) - 1;  // index start from 0
    nums[m] = nums[m] > 0 ? -nums[m] : nums[m];
  }
  std::vector<int> res;
  for (int i = 0; i < len; i++) {
    if (nums[i] > 0) res.push_back(i + 1);
  }
  return res;
}