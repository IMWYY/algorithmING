#include <vector>
#include <unordered_set>

bool isValidSudoku(std::vector<std::vector<char>>& board) {
  std::unordered_set<char> rowset, colset, boxset;

  for (int i = 0; i < 9; ++i) {
    rowset.clear();
    colset.clear();
    boxset.clear();
    for (int j = 0; j < 9; ++j) {
      if (board[i][j] != '.' && !rowset.insert(board[i][j]).second)
        return false;
      if (board[j][i] != '.' && !colset.insert(board[j][i]).second)
        return false;
      int brow = i / 3, bcol = i % 3;
      if (board[brow * 3 + j / 3][bcol * 3 + j % 3] != '.' &&
          !boxset.insert(board[brow * 3 + j / 3][bcol * 3 + j % 3]).second)
        return false;
    }
  }
  return true;
}