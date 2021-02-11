#include <vector>

/**
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If
 * the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 */
int findPeakElement(std::vector<int>& nums) {
  int n = nums.size();
  int s = 0, e = n - 1;
  // invariant: nums[s-1] < nums[s] && nums[e] > nums[e+1]
  while (s + 1 < e) {
    int m = (s + e) / 2;
    if (m + 1 < n && nums[m] < nums[m + 1])
      s = m + 1;
    else
      e = m;
  }
  return (nums[s] > nums[e] ? s : e);
}