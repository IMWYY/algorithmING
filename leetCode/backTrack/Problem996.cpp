#include <algorithm>
#include <cmath>
#include <vector>

void backTrack(std::vector<int>&, std::vector<int>&, std::vector<bool>&, int&);
int numSquarefulPerms(std::vector<int>& A) {
  int res = 0;
  std::sort(A.begin(), A.end());
  std::vector<int> list;
  std::vector<bool> used(A.size(), false);
  backTrack(A, list, used, res);
  return res;
}

void backTrack(std::vector<int>& A, std::vector<int>& list,
               std::vector<bool>& used, int& res) {
  if (list.size() == A.size()) {
    res++;
    return;
  }
  for (int i = 0; i < A.size(); ++i) {
    if (used[i] || (i > 0 && A[i] == A[i - 1] && !used[i - 1])) continue;
    // here (A[i] == A[i - 1] && !used[i - 1]) is much faster than
    // (A[i] == A[i - 1] && used[i - 1])
    if (list.size() > 0) {
      int sq = (int)std::sqrt(list.back() + A[i]);
      if (sq * sq != list.back() + A[i]) continue;
    }
    used[i] = true;
    list.push_back(A[i]);
    backTrack(A, list, used, res);
    list.pop_back();
    used[i] = false;
  }
}