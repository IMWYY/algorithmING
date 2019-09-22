#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected
 * server-to-server connections forming a network where connections[i] = [a, b]
 * represents a connection between servers a and b. Any server can reach any
 * other server directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some server
 * unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 */

// tarjan algorithm
// discovered record the rank of the vertex, the value is positive if visited
// root record the root of a cycle
void tarjan(std::vector<std::vector<int>> &graph, int prev, int cur,
            std::vector<int> &discovered, std::vector<int> &root,
            std::vector<std::vector<int>> &result) {
  static int time;
  discovered[cur] = root[cur] = ++time;
  for (int n : graph[cur]) {
    if (discovered[n] == -1) {
      tarjan(graph, cur, n, discovered, root, result);
      root[cur] = std::min(root[cur], root[n]);
      // update the root of cur vertex
      if (root[n] > discovered[cur]) {
        // if the root of the n is great than cur vertex's rank, than cur vertex
        // is not in the cycle
        result.push_back({n, cur});
      }
    } else if (n != prev) {
      root[cur] = std::min(root[cur], discovered[n]);
      // here we encounter a cycle, then update the root use the rank of visited
      // vertex n
    }
  }
}

vector<vector<int>> criticalConnections(int n,
                                        vector<vector<int>> &connections) {
  std::vector<std::vector<int>> graph;
  for (int i = 0; i < n; ++i) {
    graph.push_back(std::vector<int>());
  }

  for (std::vector<int> &c : connections) {
    graph[c[0]].push_back(c[1]);
    graph[c[1]].push_back(c[0]);
  }

  std::vector<int> discovered(n, -1), root(n, -1);
  std::vector<std::vector<int>> result;
  for (int i = 0; i < n; ++i) {
    if (discovered[i] == -1) {
      tarjan(graph, i, i, discovered, root, result);
    }
  }

  return result;
}