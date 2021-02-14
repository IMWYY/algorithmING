#include <unordered_map>
#include <vector>

int fourSumCount(std::vector<int>& A, std::vector<int>& B, std::vector<int>& C,
                 std::vector<int>& D) {
  int res = 0;
  std::unordered_map<int, int> m;

  for (auto u : A)
    for (auto v : B) m[u + v]++;
  for (auto u : C)
    for (auto v : D) {
      auto it = m.find(0 - u - v);
      if (it != m.end()) res += it->second;
    }

  return res;
}