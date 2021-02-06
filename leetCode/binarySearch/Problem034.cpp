#include <vector>

/**
 *
 * Given an array of integers nums sorted in ascending order, find the starting
 * and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * Follow up: Could you write an algorithm with O(log n) runtime complexity?
 */
std::vector<int> searchRange(std::vector<int>& nums, int target) {
  int s = 0, e = nums.size();
  // search for the fisrt val that is no less than target
  while (s < e) {
    int mid = (s + e) / 2;
    if (!(nums[mid] >= target))
      s = mid + 1;
    else
      e = mid;
  }
  if (e == nums.size() || nums[e] != target) return {-1, -1};

  int start = e, end = 0;
  s = 0;
  e = nums.size();
  // search for the fisrt val that is greater than target
  while (s < e) {
    int mid = (s + e) / 2;
    if (!(nums[mid] > target))
      s = mid + 1;
    else
      e = mid;
  }
  end = e - 1;
  return {start, end};
}