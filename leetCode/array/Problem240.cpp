#include <vector>

// search from top right
bool searchMatrix(std::vector<std::vector<int>>& matrix, int target) {
  if (matrix.size() == 0) return false;
  int m = matrix.size(), n = matrix[0].size();
  int r = 0, c = n - 1;
  while (r < m && c >= 0) {
    if (matrix[r][c] == target) return true;
    if (matrix[r][c] > target) c--;
    else r ++;
  }
  return false;
}