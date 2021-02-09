#include <algorithm>
#include <vector>

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by
 * 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly. DO NOT allocate another 2D matrix and do the
 * rotation.
 */
void rotate(std::vector<std::vector<int> > &matrix) {
  std::reverse(matrix.begin(), matrix.end());
  for (int i = 0; i < matrix.size(); ++i) {
    for (int j = i + 1; j < matrix[i].size(); ++j)
      std::swap(matrix[i][j], matrix[j][i]);
  }
}