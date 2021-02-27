#include <vector>

class NumMatrix {
 public:
  std::vector<std::vector<int>> pre_sum;
  NumMatrix(std::vector<std::vector<int>>& matrix)
      : pre_sum(matrix.size() + 1,
                std::vector<int>(
                    matrix.size() > 0 ? (matrix.front().size() + 1) : 1, 0)) {
    for (int i = 1; i <= matrix.size(); ++i) {
      int sum = 0;
      for (int j = 1; j <= matrix.front().size(); ++j) {
        sum += matrix[i - 1][j - 1];
        pre_sum[i][j] = pre_sum[i - 1][j] + sum;
      }
    }
  }

  int sumRegion(int row1, int col1, int row2, int col2) {
    return pre_sum[row2 + 1][col2 + 1] - pre_sum[row2 + 1][col1] -
           pre_sum[row1][col2 + 1] + pre_sum[row1][col1];
  }
};