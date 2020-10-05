#include <algorithm>
#include <bitset>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

/**
 *
 * Given an integer n, you must transform it into 0 using the following
 * operations any number of times:
 *
 * Change the rightmost (0th) bit in the binary representation of n.
 * Change the ith bit in the binary representation of n if the (i-1)th bit is
 * set to 1 and the (i-2)th through 0th bits are set to 0. Return the minimum
 * number of operations to transform n into 0
 */

int toLeadOne(std::string&, int);
int toAllZero(std::string&, int);

// we don't need to transform n into binary string actually...
// str[:idx] just means (n ^ (1 << (idx+1) -1))
//
// very smart and neat solution can be found in
// https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/discuss/877798/JavaC%2B%2BPython-3-Solutions-with-Prove-O(1)-Space
int minimumOneBitOperations(int n) {
  if (n == 0) return 0;
  std::string str = std::bitset<32>(n).to_string();
  return toAllZero(str, 0);
}

int toLeadOne(std::string& str, int idx) {
  if (idx == str.size() - 1 && str[idx] == '0') return 1;
  if (idx == str.size() - 1 && str[idx] == '1') return 0;
  if (str[idx] == '1')
    return toAllZero(str, idx + 1);
  else {
    return toLeadOne(str, idx + 1) + std::pow(2, str.size() - idx - 1);
  }
}

// to transform str[idx:] to 1XXXXX
int toAllZero(std::string& str, int idx) {
  if (idx == str.size() - 1 && str[idx] == '0') return 0;
  if (idx == str.size() - 1 && str[idx] == '1') return 1;
  for (int i = idx; i < str.size(); ++i) {
    if (str[i] == '1') {
      if (str.size() - i - 1 > 0) {
        return toLeadOne(str, i + 1) + std::pow(2, str.size() - i - 1);
      } else {
        return 1;
      }
    }
  }
  return 0;
}
