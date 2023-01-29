#include <climits>

int monkeyMove(int n) {
  int64_t base = 2, mod = 1e9 + 7;
  int64_t res = 1;
  while (n > 0) {
    if (n % 2) res = res * base % mod;
    base = base * base % mod;
    n /= 2;
  }
  return (res - 2 + mod) % mod;
}