#include <climits>

/**
 * it will too slow if we multipy them one by one,
 * so we try to reduce the power by call myPow recursively.
 */
double myPow(double x, int n) {
  if (x == 0) return 0;
  if (n == 0) return 1;
  if (n == 1) return x;
  if (n == INT_MIN) { // note the corner case!
    return 1 / x * myPow(x, n + 1);
  } else if (n < 0) {
    n = -n;
    x = 1 / x;
  }
  double t = myPow(x * x, n / 2);
  return n % 2 == 0 ? t : x * t;
}