#include <string>
#include <vector>

class UFSet;
bool equationsPossible(std::vector<std::string>& equations) {
  UFSet uf(26);
  for (auto& s : equations) {
    if (s[1] == '=') {
      if (s[0] == s[3]) continue;
      uf.union_two(s[0] - 'a', s[3] - 'a');
    } else {
      if (s[0] == s[3]) return false;
      if (uf.connected(s[0] - 'a', s[3] - 'a')) return false;
    }
  }
  for (auto& s : equations) {
    if (s[1] == '!' && uf.connected(s[0] - 'a', s[3] - 'a')) return false;
  }
  return true;
}

class UFSet {
 public:
  UFSet(int sz) : parent(sz, -1) {}
  int find(int x) {
    int s = x;
    for (s = x; parent[s] >= 0; s = parent[s])
      ;

    while (parent[x] >= 0) {
      int tmp = parent[x];
      parent[x] = s;
      x = tmp;
    }

    return s;
  }

  bool connected(int a, int b) { return find(a) == find(b); }

  void union_two(int a, int b) {
    // std::cout << a << ":" << b << std::endl;
    int x = find(a), y = find(b);
    if (x == y) return;
    // std::cout << x << ":" << y << std::endl;
    int n1 = parent[x], n2 = parent[y];
    if (n1 < n2) {
      parent[y] = x;
      parent[x] = n1 + n2;
    } else {
      parent[x] = y;
      parent[y] = n1 + n2;
    }
  }
  std::vector<int> parent;
}