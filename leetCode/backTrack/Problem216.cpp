#include <vector>

/**
 *
 * Find all valid combinations of k numbers that sum up to n such that the
 * following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain
 * the same combination twice, and the combinations may be returned in any
 * order.
 */
void backtrack(std::vector<std::vector<int>>&, int, int, std::vector<int>&,
               int);
std::vector<std::vector<int>> combinationSum3(int k, int n) {
  std::vector<std::vector<int>> result;
  std::vector<int> current;
  backtrack(result, k, 1, current, n);
  return result;
}

void backtrack(std::vector<std::vector<int>>& result, int k, int idx,
               std::vector<int>& current, int remain) {
  if (remain < 0 || current.size() > k) return;
  if (remain == 0) {
    if (current.size() == k) result.push_back(current);
    return;
  }
  for (int i = idx; i < 10; ++i) {
    current.push_back(i);
    backtrack(result, k, i + 1, current, remain - i);
    current.pop_back();
  }
}