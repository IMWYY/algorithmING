#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

/**
 *
 * You are given an array nums of non-negative integers. nums is considered
 * special if there exists a number x such that there are exactly x numbers in
 * nums that are greater than or equal to x.
 *
 * Notice that x does not have to be an element in nums.
 *
 * Return x if the array is special, otherwise, return -1. It can be proven that
 * if nums is special, the value for x is unique.
 */

int specialArray(std::vector<int>& nums) {
  std::sort(nums.begin(), nums.end());
  for (int i = 0; i < nums.size(); ++i) {
    int x = nums.size() - i;
    if (x <= nums[i] && (i == 0 || x > nums[i - 1])) return x;
  }
  return -1;
}