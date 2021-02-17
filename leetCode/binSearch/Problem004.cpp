#include <algorithm>
#include <climits>
#include <vector>

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)). <p> Example 1: nums1 = [1, 3] nums2 = [2] The median
 * is 2.0 <p> Example 2: nums1 = [1, 2] nums2 = [3, 4] The median is (2 + 3)/2
 * = 2.5
 */
double findMedianSortedArrays(std::vector<int>& nums1,
                              std::vector<int>& nums2) {
  if (nums1.size() < nums2.size()) nums1.swap(nums2);
  int m = nums1.size(), n = nums2.size();
  int si = 0, ei = m;
  while (si <= ei) {
    int i = (si + ei) / 2;    // i belongs to [0, m-1]
    int j = (m + n) / 2 - i;  // j belongs to [0, n]
    if (j < 0 || (j < n && i > 0 && nums1[i - 1] > nums2[j])) {
      ei = i - 1;
    } else if (j > n || (j > 0 && nums2[j - 1] > nums1[i])) {
      si = i + 1;
    } else {
      int min_right = INT_MAX;
      if (i >= 0 && i < m) min_right = std::min(min_right, nums1[i]);
      if (j >= 0 && j < n) min_right = std::min(min_right, nums2[j]);
      if ((m + n) % 2 == 1) return min_right;
      int max_left = INT_MIN;
      if (i > 0) max_left = std::max(max_left, nums1[i - 1]);
      if (j > 0) max_left = std::max(max_left, nums2[j - 1]);
      return (min_right + max_left) / 2.0;
    }
  }
  return 0.0;
}