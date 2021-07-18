
#include <climits>
#include <vector>

/**
 * You are given an integer array nums sorted in ascending order, and an integer
 * target.
 *
 * Suppose that nums is rotated at some pivot unknown to you beforehand (i.e.,
 * [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * If target is found in the array return its index, otherwise, return -1.
 */
int search(std::vector<int>& nums, int target) {
  int start = 0, end = nums.size();
  // need to consider four cases according to the relation between
  // nums[start], nums[end], nums[mid], and target
  while (start < end) {
    int m = (start + end) >> 1;
    if (nums[start] > nums[m]) {
      if (target < nums[m])
        end = m;
      else {
        if (target >= nums[start])
          end = m;
        else
          start = m;
      }
    } else if (nums[start] < nums[m]) {
      if (target >= nums[m])
        start = m;
      else {
        if (target >= nums[start])
          end = m;
        else
          start = m + 1;
      }
    } else {
      break;
    }
  }
  return (start < nums.size() && nums[start] == target) ? start : -1;
}