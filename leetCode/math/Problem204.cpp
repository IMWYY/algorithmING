#include <vector>

int countPrimes(int n) {
  int count = 0;
  std::vector<bool> notPrime(n, false);
  for (int i = 2; i < n; ++i) {
    if (!notPrime[i]) {
      count++;
      for (int j = 2; i * j < n; ++j) {
        notPrime[i * j] = true;
      }
    }
  }
  return count;
}