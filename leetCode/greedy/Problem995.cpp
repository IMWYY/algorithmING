#include <queue>
#include <vector>

// greedy approach
// from left to right, perform a flip if there A[i] is 0.
// O(kn) time
int minKBitFlips(std::vector<int>& A, int K) {
  int cnt = 0;
  for (int i = 0; i < A.size(); i++) {
    if (A[i] == 0) {
      if (i + K > A.size()) return -1;
      for (int j = i; j < i + K; j++) {
        A[j] ^= 1;
      }
      cnt++;
    }
  }
  return cnt;
}

// optimized greedy approach
// O(n) time
int minKBitFlips2(std::vector<int>& A, int K) {
  std::queue<int> q;
  int res = 0;
  for (int i = 0; i < A.size(); ++i) {
    // std::cout << q.size() << std::endl;
    if (A[i] == (q.size() % 2 == 0 ? 0 : 1)) {
      if (i + K - 1 >= A.size()) return -1;
      q.push(i + K - 1);
      res++;
    }
    if (!q.empty() && i >= q.front()) q.pop();
  }
  return res;
}