#include <vector>

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen
 * numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen
 * numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target
 * is less than 150 combinations for the given input.
 */
void backtrack(std::vector<std::vector<int>>&, std::vector<int>&, int,
               std::vector<int>&, int);
std::vector<std::vector<int>> combinationSum(std::vector<int>& candidates,
                                             int target) {
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
    current.push_back(candidates[i]);
    backtrack(result, candidates, i, current, remain - candidates[i]);
    current.pop_back();
  }
}