
#include <vector>

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest
 * possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 */
void reverse(std::vector<int>&, int, int);
void nextPermutation(std::vector<int>& nums) {
  if (nums.size() <= 1) return;
  int i = nums.size() - 2;
  while (i >= 0 && nums[i] >= nums[i + 1]) i--;
  if (i >= 0) {
    // find the last element whose value is > nums[i]
    int min_idx = i + 1;
    for (int j = i + 1; j < nums.size(); j++) {
      if (nums[j] > nums[i] && nums[j] <= nums[min_idx]) {
        min_idx = j;
      }
    }
    int tmp = nums[min_idx];
    nums[min_idx] = nums[i];
    nums[i] = tmp;
    reverse(nums, i + 1, nums.size() - 1);
  } else {
    reverse(nums, 0, nums.size() - 1);
  }
}

void reverse(std::vector<int>& nums, int start, int end) {
  while (start < end) {
    int tmp = nums[start];
    nums[start] = nums[end];
    nums[end] = tmp;
    start++;
    end--;
  }
}