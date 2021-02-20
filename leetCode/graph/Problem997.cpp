#include <vector>

int findJudge(int N, std::vector<std::vector<int>>& trust) {
  std::vector<std::vector<int>> nodes(N + 1,
                                      std::vector<int>(2, 0));  // 0:in 1:out
  for (auto& t : trust) {
    nodes[t[0]][1]++;
    nodes[t[1]][0]++;
  }

  int res = -1;
  for (int i = 1; i < nodes.size(); ++i) {
    auto& n = nodes[i];
    if (n[0] == N - 1 && n[1] == 0) {
      res = i;
    }
  }
  return res;
}