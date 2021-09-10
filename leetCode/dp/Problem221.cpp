#include <vector>

/**
 * dp[i][j] represent the side length of the square whose bottom-right position
 * is (i,j) O(mn) time + O(nm) space （ or O (n) space）
 * can be optimized to O(n) space
 */
int maximalSquare(std::vector<std::vector<int>>& matrix) {
  int rows = matrix.size(), cols = rows > 0 ? matrix[0].size() : 0;
  std::vector<std::vector<int>> dp(rows + 1, std::vector<int>(cols + 1, 0));
  int maxsqlen = 0;
  for (int i = 1; i <= rows; i++) {
    for (int j = 1; j <= cols; j++) {
      if (matrix[i - 1][j - 1] == '1') {
        dp[i][j] =
            std::min(std::min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) +
            1;
        maxsqlen = std::max(maxsqlen, dp[i][j]);
      }
    }
  }
  return maxsqlen * maxsqlen;
}