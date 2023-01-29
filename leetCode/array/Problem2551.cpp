#include <vector>
#include <algorithm>

long long putMarbles(std::vector<int>& weights, int k) {
  int n = weights.size();
  if (k == 1 || k == n) return 0;
  std::vector<int64_t> candidates;
  for (int i = 0; i < n - 1; ++i) {
    candidates.push_back(weights[i] + weights[i + 1]);
  }
  std::sort(candidates.begin(), candidates.end());
  int64_t max = 0, min = 0;
  for (int i = 0; i < k - 1; ++i) {
    min += candidates[i];
    max += candidates[candidates.size() - i - 1];
  }
  return max - min;
}