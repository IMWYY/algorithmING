#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

/**
 * Given a rows * columns matrix mat of ones and zeros, return how many
 * submatrices have all ones.
 */

int numSubmat(std::vector<std::vector<int>>& mat) {
  int m = mat.size(), n = mat[0].size();
  int res = 0;
  std::vector<int> heights(n, 0);
  for (size_t i = 0; i < m; ++i) {
    for (size_t j = 0; j < n; ++j) {
      if (mat[i][j])
        heights[j]++;
      else
        heights[j] = 0;

      int h = heights[j];
      for (int k = j; k >= 0; k--) {
        h = std::min(heights[k], h);
        if (h == 0) break;
        res += h;
      }
    }
  }
  return res;
}