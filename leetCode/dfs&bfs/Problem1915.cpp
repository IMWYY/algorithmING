#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Given a tree (i.e. a connected, undirected graph that has no cycles)
 * consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. The
 * root of the tree is the node 0, and each node of the tree has a label which
 * is a lower-case character given in the string labels (i.e. The node with the
 * number i has the label labels[i]).
 *
 * The edges array is given on the form edges[i] = [ai, bi], which means there
 * is an edge between nodes ai and bi in the tree.
 *
 * Return an array of size n where ans[i] is the number of nodes in the subtree
 * of the ith node which have the same label as node i.
 *
 * A subtree of a tree T is the tree consisting of a node in T and all of its
 * descendant nodes.
 */

void traversal(int node, std::vector<bool> &visited,
               std::vector<std::vector<int>> &undirect_edges,
               std::vector<std::vector<int>> &subtree_cnt,
               std::vector<int> &res, string &labels) {
  if (visited[node]) return;
  visited[node] = true;
  subtree_cnt[node][labels[node] - 'a']++;
  for (int child : undirect_edges[node]) {
    if (visited[child]) continue;  // avoid visit a back-track edge
    traversal(child, visited, undirect_edges, subtree_cnt, res, labels);
    for (int i = 0; i < subtree_cnt[child].size();
         ++i) {  // inherent counter from child
      subtree_cnt[node][i] += subtree_cnt[child][i];
    }
  }
  res[node] = subtree_cnt[node][labels[node] - 'a'];
}

vector<int> countSubTrees(int n, vector<vector<int>> &edges, string labels) {
  std::vector<int> res(n, 0);
  std::vector<std::vector<int>> undirect_edges(n, std::vector<int>());
  // we need consider both directions
  for (std::vector<int> e : edges) {
    undirect_edges[e[0]].push_back(e[1]);
    undirect_edges[e[1]].push_back(e[0]);
  }
  std::vector<std::vector<int>> subtree_cnt(n, std::vector<int>(26, 0));
  std::vector<bool> visited(n, false);
  traversal(0, visited, undirect_edges, subtree_cnt, res, labels);

  return res;
}