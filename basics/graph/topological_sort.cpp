
#include <assert.h>

#include <iostream>
#include <queue>
#include <vector>

/**
 * we can also get topological sort through reverse post visiting order.
 * see graph_visit.cpp.
 **/
void topological_sort(std::vector<std::vector<int>>& graph) {
  assert(graph.size() == graph[0].size());
  int vn = graph.size();
  std::vector<int> indgree(vn, 0);
  for (int i = 0; i < vn; ++i) {
    for (int j = 0; j < vn; ++j) {
      if (graph[i][j] > 0) indgree[j]++;
    }
  }

  std::queue<int> res;

  for (int i = 0; i < vn; ++i) {
    if (indgree[i] == 0) res.push(i);
  }

  int total_count = 0;

  while (!res.empty()) {
    int v = res.front();
    res.pop();
    total_count++;
    std::cout << "vertex: " << v << std::endl;
    for (int j = 0; j < vn; ++j) {
      if (graph[v][j] > 0) {
        indgree[j]--;
        if (indgree[j] == 0) res.push(j);
      }
    }
  }

  if (total_count < vn) {
    std::cout << "circle exists!" << std::endl;
  }
}

int main() {
  std::vector<std::vector<int>> graph = {{-1, 1, -1, 1, -1},
                                         {-1, -1, 1, 1, -1},
                                         {-1, -1, -1, -1, 1},
                                         {-1, -1, 1, -1, 1},
                                         {-1, -1, -1, -1, -1}};
  topological_sort(graph);
}