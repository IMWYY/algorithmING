#include <algorithm>
#include <vector>

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 */
std::vector<std::vector<int>> merge(std::vector<std::vector<int>>& A) {
  std::sort(A.begin(), A.end(), [](std::vector<int>& l, std::vector<int>& r) {
    return l[0] < r[0];
  });
  int s = A[0][0], e = A[0][1];
  std::vector<std::vector<int>> result;
  for (int i = 1; i < A.size(); ++i) {
    if (A[i][0] > e) {
      result.push_back({s, e});
      s = A[i][0];
      e = A[i][1];
    } else {
      e = std::max(A[i][1], e);
    }
  }
  result.push_back({s, e});
  return result;
}