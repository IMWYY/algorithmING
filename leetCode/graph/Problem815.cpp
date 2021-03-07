#include <climits>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <vector>

/**
 * solution 1: dijkstra algorithm
 */
int numBusesToDestination(std::vector<std::vector<int>>& routes, int source,
                          int target) {
  if (source == target) return 0;
  int n = routes.size();

  // construct the graph
  std::vector<std::vector<int>> graph(n, std::vector<int>(n, INT_MAX));
  std::unordered_map<int, std::vector<int>> mp;
  int start = -1, end = -1;
  std::vector<int> starts, ends;
  for (int i = 0; i < routes.size(); ++i) {
    for (int& stop : routes[i]) {
      if (stop == source) starts.push_back(i);
      if (stop == target) ends.push_back(i);
      if (mp.count(stop) > 0) {
        for (int j = 0; j < mp[stop].size(); ++j) {
          graph[i][mp[stop][j]] = 1;
          graph[mp[stop][j]][i] = 1;
        }
        mp[stop].push_back(i);
      } else {
        mp[stop] = {i};
      }
    }
  }

  if (starts.size() == 0 || ends.size() == 0) return -1;

  int res = INT_MAX;
  for (int start : starts) {
    for (int end : ends) {
      if (start == end) {
        res = 0;
        continue;
      }
      // std::cout << start << "-" << end << std::endl;

      std::vector<int> minpath(n, INT_MAX);
      for (int i = 0; i < n; ++i) minpath[i] = graph[start][i];
      std::vector<bool> visited(n, false);
      visited[start] = true;
      // std::cout << "here" << std::endl;

      for (int i = 1; i < n; ++i) {
        int min_v = -1, min_len = INT_MAX;
        for (int j = 0; j < n; ++j) {
          if (!visited[j] && minpath[j] < min_len) {
            min_len = minpath[j];
            min_v = j;
          }
        }
        if (min_v == -1) break;
        visited[min_v] = true;
        for (int j = 0; j < n; ++j) {
          if (graph[min_v][j] == INT_MAX) continue;
          minpath[j] = std::min(minpath[j], minpath[min_v] + graph[min_v][j]);
        }
      }
      // std::cout << "here" << std::endl;
      res = std::min(res, minpath[end]);
    }
  }
  return res == INT_MAX ? -1 : (res + 1);
}

/**
 * solution2: BFS
 */
int numBusesToDestination2(std::vector<std::vector<int>>& routes, int S,
                           int T) {
  std::unordered_map<int, std::vector<int>> to_routes;
  for (int i = 0; i < routes.size(); ++i)
    for (int j : routes[i]) to_routes[j].push_back(i);
  std::queue<std::pair<int, int>> bfs;
  bfs.push({S, 0});
  std::unordered_set<int> seen = {S};
  while (!bfs.empty()) {
    int stop = bfs.front().first, bus = bfs.front().second;
    bfs.pop();
    if (stop == T) return bus;
    for (int i : to_routes[stop]) {
      for (int j : routes[i]) {
        if (seen.find(j) == seen.end()) {
          seen.insert(j);
          bfs.push({j, bus + 1});
        }
      }
      routes[i].clear();
    }
  }
  return -1;