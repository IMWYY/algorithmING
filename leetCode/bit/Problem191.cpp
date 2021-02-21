#include <stdlib.h>

int hammingWeight(uint32_t n) {
  int bits = 0;
  while (n > 0) {
    if ((n & 1) != 0) bits++;
    n >>= 1;
  }
  return bits;
}

int hammingWeight2(uint32_t n) {
  int res = 0;
  while (n != 0) {
    n = n & (n - 1); // each operation remove the rightmost 1 bit
    res++;
  }
  return res;
}