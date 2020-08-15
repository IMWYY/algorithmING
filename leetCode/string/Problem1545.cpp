#include <cmath>
#include <iostream>
#include <numeric>
#include <sstream>
#include <string>
#include <vector>

/**
 * Given two positive integers n and k, the binary string  Sn is formed as
 * follows:
 *
 * S1 = "0"
 * Si = Si-1 + "1" + reverse(invert(Si-1)) for i > 1
 * Where + denotes the concatenation operation, reverse(x) returns the reversed
 * string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1
 * changes to 0).
 */

std::string n1 = "0";
std::string n2 = "011";
std::string n3 = "0111001";

char findKthBit(int n, int k) {
  if (n == 1) return '0';
  if (n == 2) return n2[k - 1];
  if (n == 3) return n3[k - 1];

  int len = std::pow(2, n) - 1;
  int mid = len / 2 + 1;
  if (k == mid) return '1';
  if (k < mid) return findKthBit(n - 1, k);
  int offset = k - mid;
  return findKthBit(n - 1, len / 2 - offset + 1) == '0' ? '1' : '0';
}