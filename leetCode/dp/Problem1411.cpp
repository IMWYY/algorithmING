#include <stdlib.h>

#include <vector>

/**
 * red-yellow-green -> 1-2-3
 * 121-> *212* 213 *232* 312 *313* (5 choices in the next row)
 * 123-> *212* 231 *232* 312 (4 choices in the next row)
 *
 * dp to record the number of those "121" and number of those "123"
 */
int numOfWays(int n) {
  if (n == 1) return 12;
  if (n == 2) return 54;
  std::vector<int64_t> dpa(n + 1, 0), dpb(n + 1, 0);
  dpa[1] = dpb[1] = 6;
  for (int i = 2; i <= n; ++i) {
    dpa[i] = dpa[i - 1] * 3 + dpb[i - 1] * 2;
    dpb[i] = dpa[i - 1] * 2 + dpb[i - 1] * 2;
    dpa[i] %= 1000000007;
    dpb[i] %= 1000000007;
  }
  return (dpa[n] + dpb[n]) % 1000000007;
}