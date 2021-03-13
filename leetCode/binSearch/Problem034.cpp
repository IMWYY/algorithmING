#include <functional>
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
int bisect(std::vector<int>& nums, std::function<bool(int)>& f) {
  int s = 0, e = nums.size();
  while (s < e) {  // first element >= target
    int m = (s + e) >> 1;
    if (!f(nums[m]))
      s = m + 1;
    else
      e = m;
  }
  return s;
}

std::vector<int> searchRange(std::vector<int>& nums, int target) {
  if (nums.size() == 0) return {-1, -1};
  int s = bisect(nums, [target](int a) { return a >= target; });
  if (s == nums.size() || nums[s] != target) return {-1, -1};
  int e = bisect(nums, [target](int a) { return a > target; }) - 1;
  return {s, e};
}