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
 * Given a rows * columns matrix mat of ones and zeros, return how many
 * submatrices have all ones.
 */

int numSubmat(vector<vector<int>>& mat) {
  int m = mat.size(), n = mat[0].size();
  for (int i = m - 1; i >= 0; i--) {
    for (int j = n - 2; j >= 0; j--) {
      // mat[i][j] record # of continuous 1 start at [i,j] in the ith row.
      mat[i][j] = mat[i][j] == 0 ? 0 : mat[i][j] + mat[i][j + 1];
    }
  }

  int res = 0;
  for (int i = 0; i < m; ++i) {
    for (int j = 0; j < n; ++j) {
      int cols = mat[i][j];
      res += cols;  // #matrix of 1 x n
      for (int k = i + 1; k < m; ++k) {
        cols = std::min(cols, mat[k][j]);
        if (cols == 0) break;
        res += cols;  // #matrix of (k - i + 1) x n
      }
    }
  }
  return res;
}