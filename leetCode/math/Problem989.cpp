#include <vector>

std::vector<int> addToArrayForm(std::vector<int>& A, int K) {
  std::vector<int> res;
  int i = A.size() - 1;
  while (i >= 0 || K > 0) {
    if (K == 0) {
      res.insert(res.begin(), A[i--]);
    } else {
      if (i >= 0) K += A[i--];
      res.insert(res.begin(), K % 10);
      K /= 10;
    }
  }
  return res;
}