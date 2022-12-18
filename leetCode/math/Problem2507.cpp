#include <cmath>

bool isPrime(int v) {
  if (v == 2 || v == 3 || v == 5 || v == 7) return true;
  for (int i = 2; i <= int(std::sqrt(v)); ++i) {
    if ((v % i) == 0) return false;
  }
  return true;
}

int smallestValue(int n) {
  if (isPrime(n)) return n;

  int next = 0;
  int prev = n;
  while (n > 1) {
    for (int i = 2; i <= n; ++i) {
      if ((n % i) != 0) continue;
      if (!isPrime(i)) continue;
      next += i;
      n /= i;
      break;
    }
  }
  // handle cases like 4
  if (next == prev) return next;
  return smallestValue(next);
}