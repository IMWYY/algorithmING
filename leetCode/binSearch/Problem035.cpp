#include <vector>

/**
 * Given a sorted array of distinct integers and a target value, return the
 * index if the target is found. If not, return the index where it would be if
 * it were inserted in order.
 */
int searchInsert(std::vector<int>& nums, int target) {
  int s = 0, e = nums.size();
  while (s < e) {
    int mid = (s + e) / 2;
    if (!(nums[mid] >= target))
      s = mid + 1;
    else
      e = mid;
  }
  return s;
}