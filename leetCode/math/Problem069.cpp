#include <stdlib.h>

/**
 * binary search within the value range
 * O(logn) time + O(1) space
 */
int mySqrt(int x) {
  if (x == 0) return 0;
  if (x < 4) return 1;
  int64_t left = 0, right = x / 2;
  while (left < right) {
    int64_t mid = (left + right) / 2; // use int64_t to avoid overflow
    if (mid * mid == x || (mid * mid < x && (mid + 1) * (mid + 1) > x))
      return (int)mid;
    if (mid * mid < x) left = mid + 1;
    if (mid * mid > x) right = mid - 1;
  }
  return (int)left;
}