
#include <iostream>
#include <numeric>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * Storekeeper is a game, in which the player pushes boxes around in a
 * warehouse, trying to get them to target locations.
 *
 * The game is represented by a grid of size n*m, where each element is a wall,
 * floor or a box.
 *
 * Your task is move the box 'B' to the target position 'T' under the following
 * rules:
 *
 * Player is represented by character 'S' and can move up, down, left, right in
 * the grid if its a floor (empy cell). Floor is represented by character '.'
 * that means free cell to walk. Wall is represented by character '#' that means
 * obstacle  (impossible to walk there). There is only one box 'B' and one
 * target cell 'T' in the grid. The box can be moved to an adjacent free cell by
 * standing next to the box and then moving in the direction of the box. This is
 * a push. The player cannot walk through the box. Return the minimum number of
 * pushes to move the box to the target. If there is no way to reach the target,
 * return -1.
 */

struct Point {
  int x, y;
  Point() : x(0), y(0) {}
  Point(int xx, int yy) : x(xx), y(yy) {}
};

struct State {
  int sx, sy;
  int bx, by;
  int step;
};

int dirs[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

bool dfs_can_reach(vector<vector<char>>& grid, Point& cur_ps, Point& target_ps,
                   vector<vector<int>>& flag) {
  if (cur_ps.x < 0 || cur_ps.x >= grid.size() || cur_ps.y < 0 ||
      cur_ps.y >= grid[0].size())
    return false;

  // NOTE: S can walk through B
  if (grid[cur_ps.x][cur_ps.y] == '#' || grid[cur_ps.x][cur_ps.y] == 'B')
    return false;

  if (cur_ps.x == target_ps.x && cur_ps.y == target_ps.y) return true;
  if (flag[cur_ps.x][cur_ps.y] == 1) return false;

  flag[cur_ps.x][cur_ps.y] = 1;
  for (int i = 0; i < 4; ++i) {
    int nx = cur_ps.x + dirs[i][0], ny = cur_ps.y + dirs[i][1];
    Point next_ps(nx, ny);
    if (dfs_can_reach(grid, next_ps, target_ps, flag)) return true;
  }
  return false;
}

// use DFS to check whether S can move to target point (so that S can push B)
bool can_reach(vector<vector<char>>& grid, Point& cur_ps, Point& target_ps) {
  if (target_ps.x < 0 || target_ps.x >= grid.size() || target_ps.y < 0 ||
      target_ps.y >= grid[0].size())
    return false;
  if (grid[target_ps.x][target_ps.y] == '#' ||
      grid[target_ps.x][target_ps.y] == 'B')
    return false;

  vector<vector<int>> flag(grid.size(), vector<int>(grid[0].size(), 0));
  return dfs_can_reach(grid, cur_ps, target_ps, flag);
}

int minPushBox(vector<vector<char>>& grid) {
  int n = grid.size(), m = grid[0].size();

  Point ps, pb, pt;
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      if (grid[i][j] == 'S') {
        ps.x = i;
        ps.y = j;
      } else if (grid[i][j] == 'B') {
        pb.x = i;
        pb.y = j;
      } else if (grid[i][j] == 'T') {
        pt.x = i;
        pt.y = j;
      }
    }
  }

  // visited info.
  // NOTE: different direction is different visiting info!!!!!
  vector<vector<vector<int>>> visited;
  for (int i = 0; i < n; ++i) {
    vector<vector<int>> tmp(m, vector<int>(4, 0));
    visited.push_back(tmp);
  }

  queue<State> q;
  for (int i = 0; i < 4; ++i) {
    int pb_nx = pb.x + dirs[i][0], pb_ny = pb.y + dirs[i][1];
    if (pb_nx < 0 || pb_nx >= n || pb_ny < 0 || pb_ny >= m) continue;
    if (grid[pb_nx][pb_ny] == '#') continue;

    Point target_ps(pb.x - dirs[i][0], pb.y - dirs[i][1]);
    if (can_reach(grid, ps, target_ps)) {
      q.push(State({pb.x, pb.y, pb_nx, pb_ny, 1}));
      visited[pb_nx][pb_ny][i] = 1;
    }
  }

  while (!q.empty()) {
    State state = q.front();
    q.pop();
    // retore grid infomation
    grid[ps.x][ps.y] = '.';
    grid[pb.x][pb.y] = '.';
    ps.x = state.sx;
    ps.y = state.sy;
    pb.x = state.bx;
    pb.y = state.by;
    grid[ps.x][ps.y] = 'S';
    grid[pb.x][pb.y] = 'B';
    if (pb.x == pt.x && pb.y == pt.y) return state.step;

    for (int i = 0; i < 4; ++i) {
      int pb_nx = pb.x + dirs[i][0], pb_ny = pb.y + dirs[i][1];
      if (pb_nx < 0 || pb_nx >= n || pb_ny < 0 || pb_ny >= m) continue;
      if (visited[pb_nx][pb_ny][i] == 1) continue;
      if (grid[pb_nx][pb_ny] == '#') continue;

      Point target_ps(pb.x - dirs[i][0], pb.y - dirs[i][1]);
      if (can_reach(grid, ps, target_ps)) {
        q.push(State({pb.x, pb.y, pb_nx, pb_ny, state.step + 1}));
        visited[pb_nx][pb_ny][i] = 1;
      }
    }
  }

  return -1;
}