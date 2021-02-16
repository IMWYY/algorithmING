#include <stdlib.h>

#include <vector>

// f(k+1)-f(k) = sum-n*B[n-1-k]
int maxRotateFunction(std::vector<int>& A) {
  if (A.size() == 0) return 0;
  int64_t sum = 0;  // use int64_t to avoid overflow of sum
  int f0 = 0;
  for (int i = 0; i < A.size(); ++i) {
    sum += A[i];
    f0 += i * A[i];
  }
  int f = f0, res = f0;
  for (int i = A.size() - 1; i > 0; i--) {
    f -= A.size() * A[i];  // subtract before add, to avoid overflos
    f += sum;
    res = std::max(res, f);
  }
  return res;
}