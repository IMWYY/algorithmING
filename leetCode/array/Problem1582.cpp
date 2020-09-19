#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

/**
 * Given a rows x cols matrix mat, where mat[i][j] is either 0 or 1, return the
 * number of special positions in mat.
 *
 * A position (i,j) is called special if mat[i][j] == 1 and all other elements
 * in row i and column j are 0 (rows and columns are 0-indexed).
 *
 */

int numSpecial(std::vector<std::vector<int>>& mat) {
  std::vector<int> rows(mat.size(), 0);
  std::vector<int> cols(mat[0].size(), 0);
  for (int i = 0; i < mat.size(); ++i) {
    for (int j = 0; j < mat[0].size(); ++j) {
      if (mat[i][j] == 1) {
        rows[i]++;
        cols[j]++;
      }
    }
  }

  int res = 0;
  for (int i = 0; i < mat.size(); ++i) {
    for (int j = 0; j < mat[0].size(); ++j) {
      if (mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1) res++;
    }
  }
  return res;
}