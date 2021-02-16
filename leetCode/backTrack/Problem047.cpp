#include <algorithm>
#include <cmath>
#include <vector>

void backTrack(std::vector<std::vector<int>>&, std::vector<int>&,
               std::vector<int>&, std::vector<bool>&);

std::vector<std::vector<int>> permuteUnique(std::vector<int>& nums) {
  std::vector<std::vector<int>> res;
  std::vector<int> list;
  std::vector<bool> used(nums.size(), false);
  std::sort(nums.begin(), nums.end());
  backTrack(res, nums, list, used);
  return res;
}

void backTrack(std::vector<std::vector<int>>& res, std::vector<int>& A,
               std::vector<int>& list, std::vector<bool>& used) {
  if (list.size() == A.size()) {
    res.push_back(list);
    return;
  }
  for (int i = 0; i < A.size(); ++i) {
    if (used[i] || (i > 0 && A[i] == A[i - 1] && !used[i - 1])) continue;
    // here (A[i] == A[i - 1] && !used[i - 1]) is much faster than
    // (A[i] == A[i - 1] && used[i - 1])
    // reason: !used[i-1] is false means that A[i-1] has been backtracked before
    // in a different path, ther is no need to visit again. used[i-1] means that
    // A[i-1] has just been visited in current path, we will have lots of
    // useless traversals in current path.
    used[i] = true;
    list.push_back(A[i]);
    backTrack(res, A, list, used);
    list.pop_back();
    used[i] = false;
  }
}