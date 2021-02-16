#include <stdlib.h>

#include <climits>

/**
 * handle INT_MIN first, then convert A B to positive number
 * Note that we cannot add divisor one by one, it will be too slow when the
 * divisor is small.
 */
int divide(int A, int B) {
  if (A == INT_MIN) {
    if (B == -1) return INT_MAX;
    if (B < 0)
      return 1 + divide(A - B, B);
    else
      return -1 + divide(A + B, B);
  }
  if (B == INT_MIN) return 0;
  int a = std::abs(A), b = std::abs(B);
  int res = 0, x = 0;
  while (a - b >= 0) {
    for (x = 0;; x++) {
      // to avoid overflow of (b << x)
      if (x > 0 && INT_MAX - (b << (x - 1)) < (b << (x - 1))) break;
      int tmp = b << x;
      if (a < tmp) break;
    }
    res += (1 << (x - 1));
    a -= (b << (x - 1));
  }
  return (A > 0) == (B > 0) ? res : -res;
}