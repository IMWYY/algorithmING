
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 *
 * A clear path from top-left to bottom-right has length k if and only if it is
 * composed of cells C_1, C_2, ..., C_k such that:
 *
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are
 * different and share an edge or corner) C_1 is at location (0, 0) (ie. has
 * value grid[0][0]) C_k is at location (N-1, N-1) (ie. has value
 * grid[N-1][N-1]) If C_i is located at (r, c), then grid[r][c] is empty (ie.
 * grid[r][c] == 0). Return the length of the shortest such clear path from
 * top-left to bottom-right.  If such a path does not exist, return -1.
 *  */

std::vector<std::vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0},  {-1, 0},
                                      {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
/**
 * do bfs operations both from start point and end point.
 */
int shortestPathBinaryMatrix(vector<vector<int>> &grid) {
  int row = grid.size(), col = grid[0].size();
  if (row == 0 || col == 0 || grid[0][0] != 0 || grid[row - 1][col - 1] != 0)
    return -1;
  std::unordered_set<int> begin_set, end_set;
  std::vector<int> queue;
  int len = 0;
  begin_set.insert(0);
  end_set.insert(row * col - 1);
  while (true) {
    std::unordered_set<int> *begin_ptr = &begin_set, *end_ptr = &end_set;
    if (end_set.size() < begin_set.size()) {
      begin_ptr = &end_set;
      end_ptr = &begin_set;
    }
    queue.clear();

    bool has_new = false;
    for (auto iter = begin_ptr->begin(); iter != begin_ptr->end(); iter++) {
      for (std::vector<int> &d : dirs) {
        int old_x = (*iter) / row, old_y = (*iter) - old_x * row;
        int x = old_x + d[0], y = old_y + d[1];
        int v = x * row + y;
        if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 0 &&
            begin_ptr->count(v) == 0) {
          if (!has_new) {
            has_new = true;
            len++;
          }
          if (end_ptr->count(v) > 0) {
            return len + 1;
          }
          queue.push_back(v);
        }
      }
    }

    if (queue.size() == 0) return -1;
    for (int &n : queue) begin_ptr->insert(n);
  }
  return -1;
}

/**
 * simple bfs from one end.
 */
struct Path {
  int x;
  int y;
  int len;
};

int shortestPathBinaryMatrix2(vector<vector<int>> &grid) {
  const vector<pair<int, int>> dirs = {{1, 0}, {0, 1},  {-1, 0}, {0, -1},
                                       {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
  const int n = grid.size();
  if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

  queue<Path> q;
  q.push({0, 0, 1});
  grid[0][0] = 1;
  while (!q.empty()) {
    auto [x, y, len] = q.front();
    q.pop();
    for (auto [dx, dy] : dirs) {
      int i = x + dx, j = y + dy;
      if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == 1) continue;
      if (i == n - 1 && j == n - 1) return len + 1;
      q.push({i, j, len + 1});
      grid[i][j] = 1;
    }
  }
  return -1;
}