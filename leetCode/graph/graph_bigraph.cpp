#include <assert.h>

#include <vector>

bool dfs(std::vector<std::vector<int>>&, std::vector<int>&, std::vector<bool>&,
         int);
// @graph: a graph in adjacent list
// @n_vertex_a: the number of vertex in one side
// @n_vertex_b: the number of vertex in another side
// @return max number of matching
int bigraph_max_matching(std::vector<std::vector<int>>& graph, int n_vertex_a,
                         int n_vertex_b) {
  assert(graph.size() == n_vertex_a);
  for (int i = 0; i < n_vertex_a; ++i) assert(graph[i].size() <= n_vertex_b);
  int sum = 0;
  std::vector<int> mp(n_vertex_b, -1);
  for (int va = 0; va < n_vertex_a; va++) {
    std::vector<bool> vis(n_vertex_b, false);
    if (dfs(graph, mp, vis, va)) sum++;
  }
  return sum;
}

// @return whether we can map @vertex_in_a to one vertex in b without
// violating existing restrictions
bool dfs(std::vector<std::vector<int>>& g, std::vector<int>& mp,
         std::vector<bool>& vis, int vertex_in_a) {
  for (int i = 0; i < g[vertex_in_a].size(); i++) {  // for each edge
    int vertex_in_b = g[vertex_in_a][i];
    if (!vis[vertex_in_b]) {
      vis[vertex_in_b] = true;
      if (mp[vertex_in_b] == -1 || dfs(g, mp, vis, mp[vertex_in_b])) {
        // current vetex is not matched or it can be re-matched
        mp[vertex_in_b] = vertex_in_a;
        return true;
      }
    }
  }
  return false;
}