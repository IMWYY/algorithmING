#include <assert.h>

#include <climits>
#include <cmath>

bool is_2power(int x) {
  if (x <= 0) return false;
  return (x & (x - 1)) == 0;
}

int low_bit(int x) { return x & (-x); }

int smallest_2power_ge(int x) {
  int max = 1 << 30;
  int n = x - 1;
  x |= x >> 1;
  x |= x >> 2;
  x |= x >> 4;
  x |= x >> 8;
  x |= x >> 16;
  return x < 0 ? 1 : (x >= max ? max : x + 1);
}

int main() {
  assert(is_2power(8) == 1);
  assert(is_2power(15) == 0);
  assert(low_bit(6) == 2);
  assert(smallest_2power_ge(6) == 8);
}