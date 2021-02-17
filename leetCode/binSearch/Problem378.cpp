#include <queue>
#include <vector>

// solution 1
// binary search within the value range
int kthSmallest(std::vector<std::vector<int>>& matrix, int k) {
  int n = matrix.size();
  int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
  while (lo < hi) {
    int m = (lo + hi) / 2;

    int cnt = 0, v = -1;
    for (int i = 0, j = n - 1; i < n && j >= 0; ++i) {
      while (j >= 0 && matrix[i][j] > m) j--;
      cnt += j + 1;
      if (j >= 0)
        v = std::max(
            v, matrix[i][j]);  // note that we need the value in the matrix
    }
    if (cnt == k)
      return v;
    else if (cnt < k)
      lo = m + 1;
    else
      hi = m;
  }
  return lo;
}

struct meta {
  int v, x, y;
  bool operator>(const meta& m) const { return v > m.v; }
  bool operator<(const meta& m) const { return v < m.v; }
};

// solution2: keep a min heap and pop k times
// O(klogk) time + O(mn) space
int kthSmallest2(std::vector<std::vector<int>>& matrix, int k) {
  int n = matrix.size();
  std::priority_queue<meta, std::vector<meta>, std::greater<meta>> pq;
  std::vector<std::vector<bool>> used(n, std::vector<bool>(n, false));
  pq.push({matrix[0][0], 0, 0});
  used[0][0] = true;
  for (int t = 0; t < k - 1; ++t) {
    int x = pq.top().x, y = pq.top().y;
    pq.pop();
    if (x + 1 < n && !used[x + 1][y]) {
      pq.push({matrix[x + 1][y], x + 1, y});
      used[x + 1][y] = true;
    }
    if (y + 1 < n && !used[x][y + 1]) {
      pq.push({matrix[x][y + 1], x, y + 1});
      used[x][y + 1] = true;
    }
  }
  return pq.top().v;
}

// solution 3:
// also use min heap, but we don't need extra space
// O(klogk) time + O(1) space
int kthSmallest3(std::vector<std::vector<int>>& matrix, int k) {
  int n = matrix.size();
  std::priority_queue<meta, std::vector<meta>, std::greater<meta>> pq;
  for (int i = 0; i < n; ++i) pq.push({matrix[0][i], 0, i});
  for (int t = 0; t < k - 1; ++t) {
    int x = pq.top().x, y = pq.top().y;
    pq.pop();
    if (x + 1 < n) pq.push({matrix[x + 1][y], x + 1, y});
  }
  return pq.top().v;
}
