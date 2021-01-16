#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_set>
#include <vector>

#include "graph_visit.cpp"

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
void dfs(std::vector<std::vector<int>>& adj_list, std::vector<int>& scc, int v,
         int id) {
  if (scc[v] != -1) return;
  scc[v] = id;
  std::cout << v << " ";
  for (int nextv : adj_list[v]) {
    if (scc[nextv] == -1) dfs(adj_list, scc, nextv, id);
  }
}

void kosaraju(Graph& graph) {
  Graph reverse_g = graph.reverse();
  // get reverse post visiting order on the reversed graph
  std::vector<int> reverse_post = reverse_g.reverse_post_visit();

  std::vector<int> scc(graph.vn, -1);

  int scc_id = 0;
  for (int i = 0; i < reverse_post.size(); ++i) {
    if (scc[reverse_post[i]] == -1) {
      std::cout << scc_id << ": ";
      dfs(graph.adj_list, scc, reverse_post[i], scc_id);
      std::cout << std::endl;
      scc_id++;
    }
  }
}

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

std::vector<int> dfn;
std::vector<int> low;
int dfn_idx = 0;
int scc_idx = 0;

void tarjan_helper(Graph&, int, std::vector<bool>&, std::vector<int>&);

void tarjan(Graph& g) {
  for (int i = 0; i < g.vn; ++i) {
    dfn.push_back(0);
    low.push_back(0);
  }
  for (int i = 0; i < g.vn; ++i) {
    if (!dfn[i]) {
      std::vector<bool> on_stack(g.vn, false);
      std::vector<int> stack;
      tarjan_helper(g, i, on_stack, stack);
    }
  }
}

// on_stack: whether node is visited in current stack.
void tarjan_helper(Graph& g, int u, std::vector<bool>& on_stack,
                   std::vector<int>& stack) {
  if (dfn[u] > 0) {  // visited
    return;
  }
  dfn[u] = low[u] = ++dfn_idx;
  on_stack[u] = true;
  stack.push_back(u);
  for (int& v : g.adj_list[u]) {
    if (!dfn[v]) {
      tarjan_helper(g, v, on_stack, stack);
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

int main() {
  std::vector<std::vector<int>> e = {{6, 4}, {0}, {4, 5}, {}, {1}, {}, {2, 5}};
  Graph g1(7, e);
  g1.pre_visit();
  g1.post_visit();
  g1.reverse_post_visit();

  int n = 6;
  std::vector<std::vector<int>> edges = {{1, 2}, {3}, {3, 4}, {0, 5}, {5}, {}};
  Graph g(6, edges);
  tarjan(g);
  kosaraju(g);
}