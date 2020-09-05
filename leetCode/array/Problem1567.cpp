#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * Given an array of integers nums, find the maximum length of a subarray where
 * the product of all its elements is positive.
 *
 * A subarray of an array is a consecutive sequence of zero or more values taken
 * out of that array.
 *
 * Return the maximum length of a subarray with positive product.
 */

int getMaxLen(std::vector<int>& nums) {
  int min_neg_len = 0, max_pos_len = 0;
  int res = 0;
  for (int i = 0; i < nums.size(); ++i) {
    if (nums[i] == 0) {
      min_neg_len = max_pos_len = 0;
    } else if (nums[i] > 0) {
      if (min_neg_len > 0) min_neg_len++;
      max_pos_len++;
    } else {
      int tmp_neg = min_neg_len, tmp_pos = max_pos_len;
      min_neg_len = tmp_pos + 1;
      if (tmp_neg > 0)
        max_pos_len = tmp_neg + 1;
      else
        max_pos_len = 0;
    }
    res = std::max(max_pos_len, res);
  }
  return res;
}