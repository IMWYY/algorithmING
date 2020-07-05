#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices
 * have all ones.
 *
 */
// dp based solution O(m*n)
int countSquares(vector<vector<int>>& mat) {
  int m = mat.size(), n = mat[0].size();
  int res = 0;
  for (int i = 0; i < m; ++i) {
    for (int j = 0; j < n; ++j) {
      if (i > 0 && j > 0) {
        mat[i][j] = mat[i][j] == 0
                        ? 0
                        : std::min(mat[i - 1][j],
                                   std::min(mat[i][j - 1], mat[i - 1][j - 1])) +
                              1;
      }
      res += mat[i][j];
    }
  }
  return res;
}

int countSquares1(vector<vector<int>>& mat) {
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
      if (cols == 0) continue;
      res++;
      for (int k = i + 1; k < m; ++k) {
        int len = k - i + 1;
        cols = std::min(cols, mat[k][j]);
        if (cols < len) break;
        res++;
      }
    }
  }
  return res;
}