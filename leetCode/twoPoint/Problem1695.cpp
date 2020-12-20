#include <algorithm>
#include <bitset>
#include <climits>
#include <cmath>
#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>

/**
 *You are given an array of positive integers nums and want to erase a subarray
 * containing unique elements. The score you get by erasing the subarray is
 * equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous
 *subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some
 *(l,r).
 */
int maximumUniqueSubarray(std::vector<int>& nums) {
  int start = 0, end = 0;
  std::unordered_map<int, int> his;
  int max_score = 0, score = 0;
  while (end < nums.size()) {
    if (his.count(nums[end]) == 0) {
      his[nums[end]] = end;
      score += nums[end];
    } else {
      max_score = std::max(max_score, score);

      int idx = his[nums[end]];
      for (int i = start; i <= idx; ++i) {
        his.erase(nums[i]);
        score -= nums[i];
      }
      his[nums[end]] = end;
      score += nums[end];
      start = idx + 1;
    }
    end++;
  }
  max_score = std::max(max_score, score);
  return max_score;
}