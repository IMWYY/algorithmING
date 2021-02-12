
#include <climits>
#include <vector>

/**
 * O(n) time + O(n) space
 * can be optimzed to O(1) space
 */
int findUnsortedSubarray(std::vector<int>& nums) {
  std::vector<int> rmin(nums.size(), 0), lmax(nums.size(), 0);
  int m = INT_MIN;
  for (int i = 0; i < nums.size(); ++i) {
    m = std::max(nums[i], m);
    lmax[i] = m;
  }
  m = INT_MAX;
  for (int i = nums.size() - 1; i >= 0; i--) {
    m = std::min(nums[i], m);
    rmin[i] = m;
  }

  int start = nums.size(), end = 0;
  for (int i = 0; i < nums.size(); ++i) {
    if (nums[i] > rmin[i]) {
      start = i;  // find the smallest index i whose value > min(nums[i], ...
                  // nums[nums.size()-1])
      break;
    }
  }
  for (int i = nums.size() - 1; i >= 0; i--) {
    if (nums[i] < lmax[i]) {
      end = i;  // find the largest index j whose value < max(nums[0], ...
                // nums[j])
      break;
    }
  }
  return std::max(0, end - start + 1);
}