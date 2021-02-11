#include <climits>
#include <vector>

/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 **/
int maxProduct(std::vector<int>& nums) {
  if (nums.size() == 0) return 0;
  int minpro = 1, maxpro = 1;
  int res = INT_MIN;
  for (int i = 0; i < nums.size(); ++i) {
    int oldmin = minpro, oldmax = maxpro;
    minpro = std::min(nums[i], std::min(oldmin * nums[i], oldmax * nums[i]));
    maxpro = std::max(nums[i], std::max(oldmin * nums[i], oldmax * nums[i]));
    res = std::max(res, maxpro);
  }
  return res;
}