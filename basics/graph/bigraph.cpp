#include <assert.h>

#include <vector>

bool dfs(std::vector<std::vector<int>>&, std::vector<int>&, std::vector<bool>&,
         int);
// @graph: a graph in adjacent list
// @return max number of matching
int bigraph_max_matching(std::vector<std::vector<int>>& graph, int n, int m) {
  assert(graph.size() == n);
  for (int i = 0; i < n; ++i) assert(graph[i].size() <= m);
  int sum = 0;
  std::vector<int> mp(m, -1);
  for (int i = 0; i < n; i++) {
    std::vector<bool> vis(m, false);
    if (dfs(graph, mp, vis, i)) sum++;
  }
  return sum;
}

bool dfs(std::vector<std::vector<int>>& g, std::vector<int>& mp,
         std::vector<bool>& vis, int x) {
  for (int i = 0; i < g[x].size(); i++) {  // for each edge
    int v = g[x][i];
    if (!vis[v]) {
      vis[v] = true;
      if (mp[v] == -1 || dfs(g, mp, vis, mp[v])) {
        // current vetex is not matched or it can be re-matched
        mp[v] = x;
        return true;
      }
    }
  }
  return false;
}