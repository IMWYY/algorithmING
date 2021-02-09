#include <vector>

/**
 * Given an array of non-negative integers nums, you are initially positioned at
 * the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that
 * position.
 *
 * Determine if you are able to reach the last index.
 */
bool canJump(std::vector<int>& nums) {
  if (nums.size() == 1) return true;
  int farpos = 0;
  for (int i = 0; i < nums.size(); ++i) {
    bool canjump = farpos >= i;
    if (canjump) farpos = std::max(farpos, i + nums[i]);
    if (farpos >= nums.size() - 1) return true;
  }
  return false;
}