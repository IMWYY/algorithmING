
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
int bisect(std::vector<int>&, int, int, int);

int search(std::vector<int>& nums, int target) {
  int start = 0, end = nums.size();
  while (start + 1 < end) {
    int mid = start + ((end - start) >> 1);
    if (nums[mid] > nums[start]) {
      start = mid;
    } else {
      end = mid;
    }
  }
  int split_pos = end;  // find the split position
  int p1 = bisect(nums, 0, split_pos, target);
  if (p1 != split_pos && nums[p1] == target) return p1;
  int p2 = bisect(nums, split_pos, nums.size(), target);
  if (p2 != nums.size() && nums[p2] == target) return p2;
  return -1;
}

int bisect(std::vector<int>& arr, int s, int e, int t) {
  while (s < e) {
    int m = s + ((e - s) >> 1);
    if (arr[m] >= t) {
      e = m;
    } else {
      s = m + 1;
    }
  }
  return s;
}