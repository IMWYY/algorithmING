
#include <iostream>
#include <stack>
#include <vector>

// a graph stored in adjacent list
class Graph {
 public:
  int vn;
  std::vector<std::vector<int>> adj_list;

  Graph(int v, std::vector<std::vector<int>>& edges) : vn(v), adj_list(edges) {}

  Graph reverse() {
    std::vector<std::vector<int>> re(vn, std::vector<int>());
    for (int i = 0; i < adj_list.size(); ++i) {
      for (int j = 0; j < adj_list[i].size(); ++j) {
        re[adj_list[i][j]].push_back(i);
        std::cout << "edge: " << adj_list[i][j] << "->" << i << std::endl;
      }
    }
    Graph g(vn, re);
    return g;
  }

  std::vector<int> pre_visit() {
    std::vector<bool> visited(vn, false);
    std::vector<int> res;
    for (int i = 0; i < vn; ++i) {
      if (!visited[i]) pre_visit_internal(res, visited, i);
    }
    return res;
  }

  std::vector<int> post_visit() {
    std::vector<bool> visited(vn, false);
    std::vector<int> res;
    for (int i = 0; i < vn; ++i) {
      if (!visited[i]) post_visit_internal(res, visited, i);
    }
    return res;
  }

  std::vector<int> reverse_post_visit() {
    std::vector<bool> visited(vn, false);
    std::stack<int> st;
    for (int i = 0; i < vn; ++i) {
      if (!visited[i]) reverse_post_visit_internal(visited, i, st);
    }
    std::vector<int> res;
    while (!st.empty()) {
      std::cout << "reverse post visit:" << st.top() << std::endl;
      res.push_back(st.top());
      st.pop();
    }
    return res;
  }

 private:
  void pre_visit_internal(std::vector<int>& res, std::vector<bool>& visited,
                          int v) {
    if (visited[v]) return;
    std::cout << "previst: " << v << std::endl;
    res.push_back(v);
    visited[v] = true;
    for (int nextv : adj_list[v]) {
      if (!visited[nextv]) pre_visit_internal(res, visited, nextv);
    }
  }

  void post_visit_internal(std::vector<int>& res, std::vector<bool>& visited,
                           int v) {
    if (visited[v]) return;
    visited[v] = true;
    for (int nextv : adj_list[v]) {
      if (!visited[nextv]) post_visit_internal(res, visited, nextv);
    }
    std::cout << "postvisit: " << v << std::endl;
    res.push_back(v);
  }

  void reverse_post_visit_internal(std::vector<bool>& visited, int v,
                                   std::stack<int>& reverselist) {
    if (visited[v]) return;
    visited[v] = true;
    for (int nextv : adj_list[v]) {
      if (!visited[nextv])
        reverse_post_visit_internal(visited, nextv, reverselist);
    }
    reverselist.push(v);
  }
};
