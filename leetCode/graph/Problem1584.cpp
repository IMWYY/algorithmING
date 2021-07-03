#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

#include "graph_union_find.cpp"

/**
 * You are given an array points representing integer coordinates of some points
 * on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan
 * distance between them: |xi - xj| + |yi - yj|, where |val| denotes the
 * absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are
 * connected if there is exactly one simple path between any two points.
 *
 */

// minimum spanning tree - prim algorithm, add vertice one by one
int minCostConnectPoints_prim(std::vector<std::vector<int>>& points) {
  std::unordered_set<int> alone;
  for (int i = 1; i < points.size(); ++i) {
    alone.insert(i);
  }

  std::vector<int> distance(points.size(), INT_MAX);

  int res = 0;
  int pre_next = 0;
  for (int k = 0; k < points.size() - 1; ++k) {
    int next = -1, min_dist = INT_MAX;
    for (int j : alone) {
      int dis = std::abs(points[pre_next][0] - points[j][0]) +
                std::abs(points[pre_next][1] - points[j][1]);
      distance[j] = std::min(dis, distance[j]);
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

// minimum spanning tree - Kruskal algorithm, add edge one by one
int minCostConnectPoints_kruskal(std::vector<std::vector<int>>& points) {
  std::vector<std::array<int, 3>> pq;
  for (int i = 0; i < points.size(); ++i) {
    for (int j = i + 1; j < points.size(); ++j) {
      pq.push_back({std::abs(points[i][0] - points[j][0]) +
                        std::abs(points[i][1] - points[j][1]),
                    i, j});
    }
  }

  // std::make_heap is more light-weigth than priority_queue
  std::make_heap(pq.begin(), pq.end(), std::greater<std::array<int, 3>>());
  UFSet ufset(points.size());
  int res = 0;
  while (!pq.empty()) {
    std::pop_heap(pq.begin(), pq.end(), std::greater<std::array<int, 3>>());
    const std::array<int, 3>& ele = pq.back();
    pq.pop_back();
    if (!ufset.connected(ele[1], ele[2])) {
      res += ele[0];
      int sum = ufset.union_two(ele[1], ele[2]);
      if (sum == points.size()) break;
    }
  }
  return res;
}