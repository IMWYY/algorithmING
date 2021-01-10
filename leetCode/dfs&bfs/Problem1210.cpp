#include <algorithm>
#include <array>
#include <queue>
#include <unordered_map>
#include <vector>
using namespace std;


// BFS solution
// 1. Use a queue to store the tail coordinates and orientation.
// 2. For every element in the queue, check the visited flag and move up, down
// or rotate, if possible. Here, I am using second and third bit (2 and 4) to
// track the state directly in the grid.
// 3. Return the number of steps in BFS if we reach the target; otherwise return
// -1
int minimumMoves(vector<vector<int>>& grid) {
  std::queue<std::array<int, 3>> q;
  q.push({0, 0, 0});
  int steps = 0;
  while (!q.empty()) {
    int cur_size = q.size();
    for (int i = 0; i < cur_size; ++i) {
      auto& location = q.front();
      int x = location[0], y = location[1], pos = location[2];
      if (x == grid.size() - 1 && y == grid[0].size() - 2 && pos == 0)
        return steps;

      if (pos == 0 && (grid[x][y] & 2) == 0) {
        grid[x][y] |= 2;
        // right
        if (y + 2 < grid[0].size() && (grid[x][y + 2] & 1) == 0)
          q.push({x, y + 1, 0});
        // down
        if (x + 1 < grid.size() && (grid[x + 1][y] & 1) == 0 &&
            (grid[x + 1][y + 1] & 1) == 0)
          q.push({x + 1, y, 0});
        // clockwise
        if (x + 1 < grid.size() && (grid[x + 1][y] & 1) == 0 &&
            (grid[x + 1][y + 1] & 1) == 0)
          q.push({x, y, 1});
      } else if (pos == 1 && (grid[x][y] & 4) == 0) {
        grid[x][y] |= 4;
        // right
        if (y + 1 < grid[0].size() && (grid[x][y + 1] & 1) == 0 &&
            (grid[x + 1][y + 1] & 1) == 0)
          q.push({x, y + 1, 1});
        // down
        if (x + 2 < grid.size() && (grid[x + 2][y] & 1) == 0)
          q.push({x + 1, y, 1});
        // counterclockwise
        if (y + 1 < grid[0].size() && (grid[x][y + 1] & 1) == 0 &&
            (grid[x + 1][y + 1] & 1) == 0)
          q.push({x, y, 0});
      }
      q.pop();
    }
    steps++;
  }
  return -1;
}

// following code is DFS code. time limit exceed.
// DFS is costly for this problem.
struct Location {
  int x, y, pos;
  Location(int xx, int yy, int pp) : x(xx), y(yy), pos(pp) {}
};

struct HashFunc {
  size_t operator()(const Location& key) const {
    return ((std::hash<int>()(key.x) ^ (std::hash<int>()(key.y) << 1)) >> 1) ^
           (std::hash<int>()(key.pos) << 1);
  }
};

struct EqualKey {
  bool operator()(const Location& l, const Location& r) const {
    return l.x == r.x && l.y == r.y && l.pos == r.pos;
  }
};

std::unordered_map<Location, int, HashFunc, EqualKey> memo;

int dfs(std::vector<vector<int>>& grid, int x, int y, int pos) {
  if (x == grid.size() - 1 && y == grid[0].size() - 2 && pos == 0) return 0;
  Location loc(x, y, pos);
  if (memo.count(loc) > 0) {
    // cout << "loc:" << x << "," << y <<"," << pos << endl;
    if (memo[loc] > 0) {
      return memo[loc];
    } else {
      return -1;
    }
  }

  int cur_len = INT_MAX;
  if (pos == 0) {
    // right
    if (y + 1 < grid[0].size() - 1 && grid[x][y + 2] == 0) {
      int l = dfs(grid, x, y + 1, 0);
      if (l >= 0) cur_len = std::min(cur_len, l + 1);
    }
    // down
    if (x < grid.size() - 1 && grid[x + 1][y] == 0 && grid[x + 1][y + 1] == 0) {
      int l = dfs(grid, x + 1, y, 0);
      if (l >= 0) cur_len = std::min(cur_len, l + 1);
    }
    // clockwise
    if (x < grid.size() - 1 && grid[x + 1][y] == 0 && grid[x + 1][y + 1] == 0) {
      int l = dfs(grid, x, y, 1);
      if (l >= 0) cur_len = std::min(cur_len, l + 1);
    }

  } else {
    // right
    if (y < grid[0].size() - 1 && grid[x][y + 1] == 0 &&
        grid[x + 1][y + 1] == 0) {
      int l = dfs(grid, x, y + 1, 1);
      if (l >= 0) cur_len = std::min(cur_len, l + 1);
    }

    // down
    if (x + 1 < grid.size() - 1 && grid[x + 2][y] == 0) {
      int l = dfs(grid, x + 1, y, 1);
      if (l >= 0) cur_len = std::min(cur_len, l + 1);
    }
    // counterclockwise
    if (y < grid[0].size() - 1 && grid[x][y + 1] == 0 &&
        grid[x + 1][y + 1] == 0) {
      int l = dfs(grid, x, y, 0);
      if (l >= 0) cur_len = std::min(cur_len, l + 1);
    }
  }

  // cout << x << "," << y << "," << pos << ": " << cur_len << endl;
  if (cur_len == INT_MAX) {
    memo[loc] = -1;
    return -1;
  } else {
    memo[loc] = cur_len;
    return cur_len;
  }
}

int minimumMoves(vector<vector<int>>& grid) { return dfs(grid, 0, 0, 0); }