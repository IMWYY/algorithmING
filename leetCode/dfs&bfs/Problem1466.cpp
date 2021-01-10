#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 *
 * There are n cities numbered from 0 to n-1 and n-1 roads such that there is
 * only one way to travel between two different cities (this network form a
 * tree). Last year, The ministry of transport decided to orient the roads in
 * one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [a, b] represents
 * a road from city a to b.
 *
 * This year, there will be a big event in the capital (city 0), and many people
 * want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit
 * the city 0. Return the minimum number of edges changed.
 *
 * It's guaranteed that each city can reach the city 0 after reorder.
 */
int travel(int cur_v, std::vector<bool>& visited,
           std::vector<vector<int>>& out_edge,
           std::vector<vector<int>>& in_edge) {
  visited[cur_v] = true;
  int res = 0;
  for (int v : in_edge[cur_v]) {
    res += travel(v, visited, out_edge, in_edge);
  }
  for (int v : out_edge[cur_v]) {
    if (!visited[v]) {
      res += (travel(v, visited, out_edge, in_edge) + 1);
    }
  }
  return res;
}

int minReorder(int n, vector<vector<int>>& connections) {
  std::vector<vector<int>> out_edge(n, std::vector<int>());
  std::vector<vector<int>> in_edge(n, std::vector<int>());
  for (std::vector<int>& e : connections) {
    out_edge[e[0]].push_back(e[1]);
    in_edge[e[1]].push_back(e[0]);
  }

  std::vector<bool> visited(n, false);
  return travel(0, visited, out_edge, in_edge);
}