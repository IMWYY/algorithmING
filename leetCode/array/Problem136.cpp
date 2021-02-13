#include <vector>

// use XOR operation to find the unique one
int singleNumber(std::vector<int> A, int n) {
  int result = 0;
  for (int i = 0; i < n; i++) {
    result ^= A[i];
  }
  return result;
}