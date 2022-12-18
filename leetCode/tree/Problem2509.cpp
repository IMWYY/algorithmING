#include <vector>

std::vector<int> cycleLengthQueries(int n,
                                    std::vector<std::vector<int>>& queries) {
  int m = queries.size();
  std::vector<int> res(m, 0);
  for (int i = 0; i < m; ++i) {
    int a = queries[i][0], b = queries[i][1];
    int step = 0;
    while (a != b) {
      if (a < b) std::swap(a, b);
      a /= 2;
      step++;
    }
    res[i] = step + 1;
  }
  return res;
}