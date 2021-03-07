#include <climits>
#include <string>

/**
 * Implement atoi which converts a string to an integer.
 */
int myAtoi(std::string s) {
  bool neg = false;
  int i = 0;
  // step 1
  while (i < s.size() && s[i] == ' ') i++;
  if (i == s.size()) return 0;
  // step 2
  if (s[i] == '-') {
    neg = true;
    i++;
  } else if (s[i] == '+') {
    neg = false;
    i++;
  }
  // step 3 & 4
  int res = 0;
  while (s[i] >= '0' && s[i] <= '9') {
    int v = s[i] - '0';
    if (neg) {
      if (res < INT_MIN / 10) return INT_MIN;
      res *= 10;
      if (res < INT_MIN + v) return INT_MIN;
      res -= v;
    } else {
      if (res > INT_MAX / 10) return INT_MAX;
      res *= 10;
      if (res > INT_MAX - v) return INT_MAX;
      res += v;
    }
    i++;
  }
  return res;
}