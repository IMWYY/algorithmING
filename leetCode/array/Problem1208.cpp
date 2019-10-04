#include <algorithm>
#include <array>
#include <queue>
#include <unordered_map>
#include <vector>
#include <string>
using namespace std;

// sliding window solution
int equalSubstring(string s, string t, int maxCost) {
  std::vector<int> costs(s.size(), 0);
  for (int i = 0; i < s.size(); ++i) {
    costs[i] = std::abs(s[i] - t[i]);
  }

  int max_len = 0, b = 0, e = 0;
  int cur_cost = 0;
  while (b <= costs.size() - max_len) {
    while (e < costs.size() && cur_cost + costs[e] <= maxCost) {
      cur_cost += costs[e];
      e++;
    }
    max_len = std::max(max_len, e - b);
    if (e == costs.size()) break;

    cur_cost -= costs[b++];
  }
  return max_len;
}