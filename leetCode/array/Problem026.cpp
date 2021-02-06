#include <vector>

/**
 * Given a sorted array nums, remove the duplicates in-place such that each
 * element appears only once and returns the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 *
 */
int removeDuplicates(std::vector<int>& nums) {
  if (nums.size() == 0) return 0;
  int idx = 0;
  for (int i = 1; i < nums.size(); ++i) {
    if (nums[i] != nums[i - 1]) nums[++idx] = nums[i];
  }
  return idx + 1;
}