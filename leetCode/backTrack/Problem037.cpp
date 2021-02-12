#include <vector>

void solve(std::vector<std::vector<char>>&, std::vector<std::vector<int>>&,
           std::vector<std::vector<int>>&, std::vector<std::vector<int>>&, int,
           bool&);
void solveSudoku(std::vector<std::vector<char>>& board) {
  std::vector<std::vector<int>> rows(9, std::vector<int>(9, 0));
  std::vector<std::vector<int>> cols(9, std::vector<int>(9, 0));
  std::vector<std::vector<int>> boxes(9, std::vector<int>(9, 0));
  int firsti = -1, firstj = 0;
  for (int i = 0; i < board.size(); ++i) {
    for (int j = 0; j < board[0].size(); ++j) {
      if (board[i][j] != '.') {
        int v = board[i][j] - '1', bi = (i / 3) * 3 + j / 3;
        boxes[bi][v] = cols[j][v] = rows[i][v] = 1;
      } else if (firsti == -1) {
        firsti = i;
        firstj = j;
      }
    }
  }
  bool success = false;
  solve(board, rows, cols, boxes, firsti * 9 + firstj, success);
}

void solve(std::vector<std::vector<char>>& board,
           std::vector<std::vector<int>>& rows,
           std::vector<std::vector<int>>& cols,
           std::vector<std::vector<int>>& boxes, int idx, bool& success) {
  if (idx == 81) {
    success = true;
    return;
  }
  if (success) return;
  int r = idx / 9, c = idx % 9;
  if (board[r][c] != '.') {
    solve(board, rows, cols, boxes, idx + 1, success);
    return;
  }

  for (int v = 0, b = (r / 3) * 3 + c / 3; v < 9; v++) {
    if (!rows[r][v] && !cols[c][v] && !boxes[b][v]) {
      board[r][c] = '1' + v;
      rows[r][v] = cols[c][v] = boxes[b][v] = 1;
      solve(board, rows, cols, boxes, idx + 1, success);
      if (success) return;
      rows[r][v] = cols[c][v] = boxes[b][v] = 0;
      board[r][c] = '.';
    }
  }
}