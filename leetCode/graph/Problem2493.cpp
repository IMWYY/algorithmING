#include <queue>
#include <vector>
#include <unordered_map>

class UnionFind {
 public:
  UnionFind(int n) : data(n, -1) {}

  int find(int x) {
    while (data[x] >= 0) {
      x = data[x];
    }
    return x;
  }

  void merge(int x, int y) {
    int r1 = find(x), r2 = find(y);
    if (r1 != r2) data[r2] = r1;
  }

 private:
  std::vector<int> data;
};

int bfs(int n, int v, const std::vector<std::vector<int>>& g) {
  std::queue<int> q;
  q.push(v);
  std::vector<int> grp(n + 1, -1);
  grp[v] = 1;
  int res = 0;
  while (!q.empty()) {
    int e = q.front();
    q.pop();
    int curG = grp[e];
    res = std::max(res, curG);
    for (auto next : g[e]) {
      if (grp[next] != -1) {
        if (grp[next] != curG - 1 && grp[next] != curG + 1) {
          return -1;
        }
      } else {
        grp[next] = curG + 1;
        q.push(next);
      }
    }
  }
  return res;
}

int magnificentSets(int n, std::vector<std::vector<int>>& edges) {
  std::vector<std::vector<int>> g(n + 1, std::vector<int>());
  UnionFind uf(n + 1);
  for (auto e : edges) {
    g[e[0]].push_back(e[1]);
    g[e[1]].push_back(e[0]);
    uf.merge(e[0], e[1]);
  }

  std::unordered_map<int, int> mp;
  for (int v = 1; v <= n; ++v) {
    int l = bfs(n, v, g);
    if (l == -1) return -1;
    int r = uf.find(v);
    mp[r] = std::max(mp[r], l);
  }

  int res = 0;
  for (auto& [_, l] : mp) res += l;

  return res;
}
