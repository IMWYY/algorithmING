#include <climits>
#include <string>

/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many
 * numerical digits as possible, and interprets them as a numerical value. <p>
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of this
 * function. <p> If the first sequence of non-whitespace characters in str is
 * not a valid integral number, or if no such sequence exists because either str
 * is empty or it contains only whitespace characters, no conversion is
 * performed. <p> If no valid conversion could be performed, a zero value is
 * returned. <p> Note: <p> Only the space character ' ' is considered as
 * whitespace character. Assume we are dealing with an environment which could
 * only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 * If the numerical value is out of the range of representable values, INT_MAX
 * (231 − 1) or INT_MIN (−231) is returned.
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