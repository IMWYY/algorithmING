#include <climits>
#include <vector>

/*
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest sum and return its sum.
 */
int maxSubArray(std::vector<int>& nums) {
  int cursum = 0, res = INT_MIN;
  for (int i = 0; i < nums.size(); ++i) {
    cursum += nums[i];
    res = std::max(res, cursum);
    if (cursum <= 0) cursum = 0;
  }
  return res;
}