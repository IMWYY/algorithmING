#include <stdlib.h>

uint32_t reverseBits(uint32_t n) {
  uint32_t res;
  for (int i = 0; i < 32; ++i) {
    res <<= 1;
    if ((n & 1) == 1) res |= 1;
    n >>= 1;
  }
  return res;
}

/**
 * for 8 bit binary number abcdefgh, the process is as follow:
 * abcdefgh -> efghabcd -> ghefcdab -> hgfedcba
 */
int reverseBits2(int n) {
  n = (n >> 16) | (n << 16);
  n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8); // eight bits
  n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4); // four bits
  n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);  // 1100 two bits
  n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);  // 1010 one bits
  return n;
}