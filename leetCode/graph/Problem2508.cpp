#include <unordered_set>
#include <vector>

bool isPossible(int n, std::vector<std::vector<int>>& edges) {
  // using std::vector<std::vector> will get a TLE
  std::vector<std::unordered_set<int>> g(n + 1, std::unordered_set<int>());
  std::vector<int> v(n + 1, 0);
  for (auto e : edges) {
    v[e[0]]++;
    v[e[1]]++;
    g[e[0]].insert(e[1]);
    g[e[1]].insert(e[0]);
  }

  std::vector<int> odd;
  for (int i = 1; i <= n; ++i) {
    if ((v[i] % 2) == 1) odd.push_back(i);
  }
  if (odd.size() == 0) return true;
  if (odd.size() == 2) {
    if (!g[odd[0]].count(odd[1])) return true;
    for (int i = 1; i <= n; ++i) {
      if (i == odd[0] || i == odd[1]) continue;
      if (!g[i].count(odd[0]) && !g[i].count(odd[1])) return true;
    }
  }
  if (odd.size() == 4) {
    if (!g[odd[0]].count(odd[1]) && !g[odd[2]].count(odd[3])) return true;
    if (!g[odd[0]].count(odd[2]) && !g[odd[1]].count(odd[3])) return true;
    if (!g[odd[0]].count(odd[3]) && !g[odd[1]].count(odd[2])) return true;
  }
  return false;
}