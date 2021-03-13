
#include <assert.h>

#include <algorithm>
#include <array>
#include <climits>
#include <iostream>
#include <unordered_set>
#include <vector>
/**
 * Three algorithms for Shortest Path problem.
 */

/**
 * dijkstra algorithm calculates single-source shortest path.
 * It can only handle non-negetive edges.
 *
 * O(V^2) time + O(V) space
 **/
int dijkstra(std::vector<std::vector<int>>& edges, int s, int e) {
  int vn = edges.size();
  assert(edges[0].size() == vn);
  assert(s >= 0 && s < vn);
  assert(e >= 0 && e < vn);
  for (int i = 0; i < vn; ++i) {
    for (int j = 0; j < vn; ++j) {
      assert(edges[i][j] >= 0);
    }
  }

  // initialize the shortest path
  std::vector<int> shortest(vn, 0);
  for (int i = 0; i < vn; ++i) {
    shortest[i] = edges[s][i];
  }

  std::unordered_set<int> added;
  while (added.size() < vn) {
    int smallv = -1;
    int smalledge = INT_MAX;
    // get the vertex with shortest path
    // note: this can be optimized with a min heap, but the complexity is still
    // O(V^2)
    for (int i = 0; i < shortest.size(); ++i) {
      if (added.count(i) > 0) continue;
      if (shortest[i] < smalledge) {
        smallv = i;
        smalledge = shortest[i];
      }
    }

    // update existing shortest path
    for (int i = 0; i < shortest.size(); ++i) {
      if (added.count(i) > 0 || edges[smallv][i] == INT_MAX) continue;
      shortest[i] = std::min(shortest[i], shortest[smallv] + edges[smallv][i]);
    }
    added.insert(smallv);
  }
  return shortest[e];
}

/**
 * bellman ford algorithm, can detect negative circle.
 *
 * O(VE) time + O(V) space
 */
void bellman_ford(std::vector<std::vector<int>>& edges, int start) {
  int vn = edges.size();
  assert(edges[0].size() == vn);
  assert(start >= 0 && start < vn);

  std::vector<int> d(vn, 0);
  for (int i = 0; i < vn; ++i) {
    d[i] = edges[start][i];
  }

  // here we use adjacent matrix to store graph, actually it is more efficient
  // to use a adjacent list when using bellmanford algorithm.
  for (int round = 0; round < vn - 1; ++round) {
    for (int i = 0; i < vn; ++i) {
      for (int j = 0; j < vn; ++j) {
        if (edges[i][j] == INT_MIN) continue;  // there is no edge
        // for each edge, update the result
        d[j] = std::min(d[j], d[i] + edges[i][j]);
        // this can be optimized by a queue, add the vertex j to queue once
        // d[i] is updated by d[j] + edges[j][i]
      }
    }
  }

  // after |V|-1 round, if we still can get smaller path, then there must be a
  // negative circle
  for (int i = 0; i < vn; ++i) {
    for (int j = 0; j < vn; ++j) {
      if (edges[i][j] != INT_MIN && d[i] + edges[i][j] < d[j]) {
        std::cout << "negative circle exists!" << std::endl;
        break;
      }
    }
  }
}

/**
 * floyd algorithm:
 * 1. can handle negative edge, but cannot handle negetive circle.
 * 2. can calcuate the shortest path between any two vertex.
 *
 * This is actually a dynamic programming
 * dp[k][i][j] is the shortest path from i to j, visiting only first k vertexes
 * dp[k][i][j] = min(dp[k-1][i][j], dp[k-1][i][k] + dp[k-1][k][j])
 * it can be optimized with a rolling array
 *
 * O(V^3) time + O(V^3) space
 **/
void print_path(std::vector<std::vector<int>>&, int, int);
void floyd(std::vector<std::vector<int>>& edges) {
  int vn = edges.size();
  assert(edges[0].size() == vn);
  std::vector<std::vector<int>> res(edges);
  std::vector<std::vector<int>> path(vn, std::vector<int>(vn, -1));

  for (int k = 0; k < vn; ++k) {
    for (int i = 0; i < vn; ++i) {
      for (int j = 0; j < vn; ++j) {
        if (res[i][k] + res[k][j] < res[i][j]) {
          res[i][j] = res[i][k] + res[k][j];
          path[i][j] = k;
        }
      }
    }
  }

  // print path
  for (int i = 0; i < vn; ++i) {
    for (int j = 0; j < vn; ++j) {
      if (res[i][j] > 0) {
        print_path(path, i, j);
        std::cout << ":" << res[i][j] << std::endl;
      }
    }
  }
}

void print_path(std::vector<std::vector<int>>& path, int i, int j) {
  if (path[i][j] == -1) {
    std::cout << i << " " << j << " ";
  } else {
    int k = path[i][j];
    print_path(path, i, k);
    print_path(path, k, j);
  }
}
