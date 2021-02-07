
#include <vector>

/**
 * Given an unsorted integer array nums, find the smallest missing positive
 * integer.
 */
int firstMissingPositive(std::vector<int>& nums) {
  for (int i = 0; i < nums.size(); ++i) {
    while (nums[i] != i + 1) {
      if (nums[i] > 0 && nums[i] <= nums.size() &&
          nums[nums[i] - 1] != nums[i]) {
        int idx = nums[i] - 1;
        int tmp = nums[i];
        nums[i] = nums[idx];
        nums[idx] = tmp;
      } else {
        break;
      }
    }
  }
  for (int i = 0; i < nums.size(); ++i) {
    if (nums[i] != i + 1) return i + 1;
  }
  return nums.size() + 1;
}