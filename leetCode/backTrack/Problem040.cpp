#include <algorithm>
#include <vector>

/**
 * Given a collection of candidate numbers (candidates) and a target number
 * (target), find all unique combinations in candidates where the candidate
 * numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 */
void backtrack(std::vector<std::vector<int>>&, std::vector<int>&, int,
               std::vector<int>&, int);

std::vector<std::vector<int>> combinationSum2(std::vector<int>& candidates,
                                              int target) {
  std::sort(candidates.begin(), candidates.end());
  std::vector<std::vector<int>> result;
  std::vector<int> current;
  backtrack(result, candidates, 0, current, target);
  return result;
}

void backtrack(std::vector<std::vector<int>>& result,
               std::vector<int>& candidates, int idx, std::vector<int>& current,
               int remain) {
  if (remain < 0) return;
  if (remain == 0) {
    result.push_back(current);
    return;
  }
  for (int i = idx; i < candidates.size(); ++i) {
    // a trick here to avoid duplicate element
    if (i > idx && candidates[i] == candidates[i - 1]) continue;
    current.push_back(candidates[i]);
    backtrack(result, candidates, i + 1, current, remain - candidates[i]);
    current.pop_back();
  }
}