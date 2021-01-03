#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

/**
 * Two algorithms for Strongly Connected Components
 */

/**
 * 1. Kosaraju.
 * DFS on the reversed graph G' and each node is marked with previsit number and
 * postvisit number. The node with the highest postvisit number is the source of
 * G', which is the sink of G. DFS on the sink of G to get the first SCC. Remove
 * the nodes in the SCC and repeat the procedure to get all SCCs.
 */

/**
 * 2.Tarjan.
 * DFS on the graph. Each node is marked with two stamps.
 * DNF: the visit number of the node.
 * LOW: the smallest node that this node can track through back edge or tree
 * edge.
 * low[u] = min {
 *  dfn[u],
 *  low(v), (u,v) is a tree edge
 *  dfn(v), (u, v) is a back edg
 * }
 * If low is equal to dfn, then we find one SCC.
 */

void tarjan(int, std::vector<bool>&, std::vector<int>&);

std::vector<std::vector<int>> edges;
std::vector<int> dfn;
std::vector<int> low;
int dfn_idx = 0;
int scc_idx = 0;

int main() {
  int n = 6;
  edges.push_back({1, 2});  // 0
  edges.push_back({3});     // 1
  edges.push_back({3, 4});  // 2
  edges.push_back({0, 5});  // 3
  edges.push_back({5});     // 4
  edges.push_back({});      // 5
  for (int i = 0; i < n; ++i) {
    dfn.push_back(0);
    low.push_back(0);
  }
  for (int i = 0; i < n; ++i) {
    if (!dfn[i]) {
      std::vector<bool> on_stack(n, false);
      std::vector<int> stack;
      tarjan(i, on_stack, stack);
    }
  }
}

// on_stack: whether node is visited in current stack.
void tarjan(int u, std::vector<bool>& on_stack, std::vector<int>& stack) {
  if (dfn[u] > 0) {  // visited
    return;
  }
  dfn[u] = low[u] = ++dfn_idx;
  on_stack[u] = true;
  stack.push_back(u);
  for (int& v : edges[u]) {
    if (!dfn[v]) {
      tarjan(v, on_stack, stack);
      low[u] = std::min(low[u], low[v]);
    } else if (on_stack[v]) {
      low[u] = std::min(low[u], dfn[v]);
    }
  }

  if (dfn[u] == low[u]) {  // we find a scc
    std::cout << ++scc_idx << ": ";
    do {
      int vertice = stack.back();
      std::cout << vertice << " ";
      stack.pop_back();
      on_stack[vertice] = false;
      if (vertice == u) break;
    } while (stack.size() > 0);
    std::cout << std::endl;
  }
}