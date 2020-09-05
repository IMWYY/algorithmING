#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * Given a 2D grid consisting of 1s (land) and 0s (water).  An island is a
 * maximal 4-directionally (horizontal or vertical) connected group of 1s.
 *
 * The grid is said to be connected if we have exactly one island, otherwise is
 * said disconnected.
 *
 * In one day, we are allowed to change any single land cell (1) into a water
 * cell (0).
 *
 * Return the minimum number of days to disconnect the grid.
 *
 */

void dfs(std::vector<std::vector<int>>& grid,
         std::vector<std::vector<bool>>& visited, int i, int j, int val);
bool is_disconnected(std::vector<std::vector<int>>& grid);

// need at most two days to spilt an island
int minDays(std::vector<std::vector<int>>& grid) {
  if (is_disconnected(grid)) return 0;
  for (int i = 0; i < grid.size(); i++) {
    for (int j = 0; j < grid[0].size(); ++j) {
      if (grid[i][j] == 1) {
        grid[i][j] = 0;
        if (is_disconnected(grid)) {
          return 1;
        }
        grid[i][j] = 1;
      }
    }
  }
  return 2;
}

void dfs(std::vector<std::vector<int>>& grid,
         std::vector<std::vector<bool>>& visited, int i, int j, int val) {
  if (visited[i][j]) return;
  if (grid[i][j] != val) return;
  visited[i][j] = true;
  if (i - 1 >= 0) dfs(grid, visited, i - 1, j, val);
  if (i + 1 < grid.size()) dfs(grid, visited, i + 1, j, val);
  if (j - 1 >= 0) dfs(grid, visited, i, j - 1, val);
  if (j + 1 < grid[0].size()) dfs(grid, visited, i, j + 1, val);
}

bool is_disconnected(std::vector<std::vector<int>>& grid) {
  std::vector<std::vector<bool>> visited(
      grid.size(), std::vector<bool>(grid[0].size(), false));
  bool travalled = false;
  for (int i = 0; i < grid.size(); i++) {
    for (int j = 0; j < grid[0].size(); ++j) {
      if (!travalled && grid[i][j] == 1) {
        dfs(grid, visited, i, j, 1);
        travalled = true;
      }
      if (travalled) {
        if (!visited[i][j] && grid[i][j] == 1) return true;
      }
    }
  }
  if (!travalled) return true;
  return false;
}
