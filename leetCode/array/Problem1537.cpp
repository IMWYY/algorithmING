#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 *
 * A valid path is defined as follows:
 *
 * Choose array nums1 or nums2 to traverse (from index-0).
 * Traverse the current array from left to right.
 * If you are reading any value that is present in nums1 and nums2 you are
 * allowed
 * to change your path to the other array. (Only one repeated value is
 * considered
 * in the valid path). Score is defined as the sum of uniques values in a valid
 * path.
 *
 * Return the maximum score you can obtain of all possible valid paths.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 */

// keep two index, and traverse the two array at the same time
int maxSum(std::vector<int>& nums1, std::vector<int>& nums2) {
  long long int X = 0, Y = 0;
  long long int x = 0, y = 0;
  while (x < nums1.size() and y < nums2.size()) {
    if (nums1[x] < nums2[y])
      X += nums1[x++];
    else if (nums1[x] > nums2[y])
      Y += nums2[y++];
    else {
      X = Y = nums1[x] + std::max(X, Y);  // this is the key part, update X and
                                          // Y to be the max upon a same element
      x++;
      y++;
    }
  }
  while (x < nums1.size()) X += nums1[x++];
  while (y < nums2.size()) Y += nums2[y++];

  return std::max(X, Y) % 1000000007;
}

// record the prefix sum and the mapping of common element index
// then travese the whole array to get the result.
int maxSum(std::vector<int>& nums1, std::vector<int>& nums2) {
  std::vector<uint64_t> prefix_sum2(nums2.size(), 0);
  std::unordered_map<int, int> val2_idx;
  for (int i = 0; i < nums2.size(); ++i) {
    prefix_sum2[i] =
        (i == 0 ? nums2[0]
                : ((uint64_t)prefix_sum2[i - 1] + (uint64_t)nums2[i]));
    val2_idx[nums2[i]] = i;
  }

  std::vector<uint64_t> prefix_sum1(nums1.size(), 0);
  std::vector<std::vector<int>> idx_map;
  for (int i = 0; i < nums1.size(); ++i) {
    prefix_sum1[i] =
        (i == 0 ? nums1[0]
                : ((uint64_t)prefix_sum1[i - 1] + (uint64_t)nums1[i]));
    if (val2_idx.count(nums1[i]) > 0) {
      std::vector<int> m(2, 0);
      m[0] = i;
      m[1] = val2_idx[nums1[i]];
      idx_map.push_back(m);
    }
  }

  uint64_t base = (uint64_t)std::pow(10, 9) + 7;
  int sum = 0;
  int i1 = 0, i2 = 0, i3 = 0;
  while (i1 < nums1.size() && i2 < nums2.size()) {
    uint64_t s1 = 0, s2 = 0;
    if (i3 >= idx_map.size()) {
      s1 =
          prefix_sum1[prefix_sum1.size() - 1] - (i3 == 0 ? 0 : prefix_sum1[i1]);
      s2 =
          prefix_sum2[prefix_sum2.size() - 1] - (i3 == 0 ? 0 : prefix_sum2[i2]);
      i1 = prefix_sum1.size() - 1;
      i2 = prefix_sum2.size() - 1;
    } else if (i3 == 0) {
      std::vector<int>& m = idx_map[i3];
      s1 = prefix_sum1[m[0]];
      s2 = prefix_sum2[m[1]];
      i1 = m[0];
      i2 = m[1];
      i3++;
    } else {
      std::vector<int>& m = idx_map[i3];
      s1 = prefix_sum1[m[0]] - prefix_sum1[i1];  // i1 exclusive (i1, ..., m[0]]
      s2 = prefix_sum2[m[1]] - prefix_sum2[i2];  // i2 exclusive
      i1 = m[0];
      i2 = m[1];
      i3++;
    }
    sum += std::max(s1, s2) % base;  // add the max of the two sum
    sum %= base;
  }

  return sum;
}