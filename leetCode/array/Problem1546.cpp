#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given an array nums and an integer target.
 *
 * Return the maximum number of non-empty non-overlapping subarrays such that
 * the sum of values in each subarray is equal to target.
 */

// using a hash set to record when previous prefix sum
int maxNonOverlapping(vector<int>& nums, int target) {
  int prefix_sum = 0, res = 0;
  std::unordered_set<int> dict;
  dict.insert(0);  // to enable subarray starting from index 0
  for (int i = 0; i < nums.size(); ++i) {
    prefix_sum += nums[i];
    int s = prefix_sum - target;
    if (dict.find(s) != dict.end()) {
      res++;
      prefix_sum = 0;
      dict.clear();
      dict.insert(0);
    } else {
      dict.insert(prefix_sum);
    }
  }
  return res;
}

// time exceed limit
int maxNonOverlapping1(vector<int>& nums, int target) {
  std::vector<int> prefix_sum(nums.size(), 0);
  prefix_sum[0] = nums[0];
  for (int i = 1; i < nums.size(); ++i) {
    prefix_sum[i] = prefix_sum[i - 1] + nums[i];
  }

  int res = 0;
  int start = 0, end = nums.size() - 1;
  bool new_round = true;
  while (start < nums.size()) {
    for (int i = start; i <= end; ++i) {
      if ((prefix_sum[i] - (start - 1 < 0 ? 0 : prefix_sum[start - 1])) ==
          target) {
        end = i;
        if (new_round) res++;
        new_round = false;
        break;
      }
    }
    start++;
    if (start > end) {
      new_round = true;
      start = end + 1;
      end = nums.size() - 1;
    }
  }
  return res;
}