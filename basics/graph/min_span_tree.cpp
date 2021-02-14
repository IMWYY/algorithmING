#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

#include "basics/graph/union_find.cpp"

// see Problem1584

/**
 * minimum spanning tree - prim algorithm, add vertice one by one
 * suitable for dense graph, which is typically respesented in adjacent list
 */
int prim(int vertice_n, std::vector<std::vector<int>>& edges) {
  std::unordered_set<int> alone;
  for (int i = 1; i < vertice_n; ++i) {
    alone.insert(i);
  }

  std::vector<int> distance(vertice_n, INT_MAX);

  int res = 0;
  int pre_next = 0;
  for (int k = 0; k < vertice_n - 1; ++k) {
    int next = -1, min_dist = INT_MAX;
    for (int j : alone) {
      // update min distance array with all edges starting from the previous
      // added node
      distance[j] = std::min(edges[pre_next][j], distance[j]);
      if (distance[j] < min_dist) {
        min_dist = distance[j];
        next = j;
      }
    }
    res += min_dist;
    alone.erase(next);
    pre_next = next;
  }
  return res;
}

/**
 * minimum spanning tree - Kruskal algorithm, add edge one by one
 * suitable for sparse graph, which is typically respesented in adjacent matrix
 */
int kruskal(int vertice_n, std::vector<std::vector<int>>& edges) {
  std::vector<std::array<int, 3>> pq;
  for (int i = 0; i < vertice_n; ++i) {
    for (int j = i + 1; j < vertice_n; ++j) {
      pq.push_back({edges[i][j], i, j});
    }
  }

  // std::make_heap is more light-weigth than priority_queue
  // construct a min heap
  std::make_heap(pq.begin(), pq.end(), std::greater<std::array<int, 3>>());
  UFSet ufset(vertice_n);
  int res = 0;
  while (!pq.empty()) {
    std::pop_heap(pq.begin(), pq.end(), std::greater<std::array<int, 3>>());
    const std::array<int, 3>& ele = pq.back();
    pq.pop_back();
    if (!ufset.connected(ele[1], ele[2])) {
      res += ele[0];
      int sum = ufset.union_two(ele[1], ele[2]);
      if (sum == vertice_n) break;
    }
  }
  return res;
}