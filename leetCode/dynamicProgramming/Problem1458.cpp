#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given two arrays nums1 and nums2.
 *
 * Return the maximum dot product between non-empty subsequences of nums1 and
 * nums2 with the same length.
 *
 * A subsequence of a array is a new array which is formed from the original
 * array by deleting some (can be none) of the characters without disturbing the
 * relative positions of the remaining characters. (ie, [2,3,5] is a subsequence
 * of [1,2,3,4,5] while [1,5,3] is not).
 */

int maxDotProduct(vector<int>& nums1, vector<int>& nums2) {
  int m = nums1.size(), n = nums2.size();
  std::vector<std::vector<int>> dp(m, std::vector<int>(n, INT_MIN));
  for (int i = 0; i < m; ++i) {
    for (int j = 0; j < n; ++j) {
      if (!i) dp[i][j] = std::max(nums1[0] * nums2[j], dp[i][j]);
      if (i) dp[i][j] = std::max(dp[i - 1][j], dp[i][j]);
      if (j) dp[i][j] = std::max(dp[i][j - 1], dp[i][j]);
      dp[i][j] = std::max(
          ((i && j) ? std::max(dp[i - 1][j - 1], 0) : 0) + nums1[i] * nums2[j],
          dp[i][j]);
    }
  }

  return dp[m - 1][n - 1];
}