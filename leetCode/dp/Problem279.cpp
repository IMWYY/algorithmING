#include <cmath>
#include <vector>

/**
 * dynamic programming
 */
int numSquares(int n) {
  if (n < 4) return n;
  std::vector<int> dp(n + 1, 1);
  for (int i = 0; i < n + 1; ++i) {
    if (std::pow((int)std::sqrt(i), 2) != i) {
      int cur = i;
      for (int j = 1; j * j < i; ++j) {
        cur = std::min(cur, dp[i - j * j] + 1);
      }
      dp[i] = cur;
    }
  }
  return dp[n];
}

/**
 * Based on Lagrange's Four Square theorem, there are only 4 possible results:
 * 1, 2, 3, 4. https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem
 * https://www.alpertron.com.ar/4SQUARES.HTM
 */
int numSquares1(int n) {
  // If n is a perfect square, return 1.
  if (std::pow((int)std::sqrt(n), 2) == n) {
    return 1;
  }
  // The result is 4 if and only if n can be written in the
  // form of 4^k*(8*m + 7). Please refer to
  // Legendre's three-square theorem.
  while ((n & 3) == 0) {  // n%4 == 0
    n >>= 2;
  }
  if ((n & 7) == 7) {  // n%8 == 7
    return 4;
  }

  // Check whether 2 is the result.
  int sqrt_n = (int)(std::sqrt(n));
  for (int i = 1; i <= sqrt_n; i++) {
    if (std::pow((int)std::sqrt(n - i * i), 2) == (n - i * i)) {
      return 2;
    }
  }
  return 3;
}