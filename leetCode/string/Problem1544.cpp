#include <cmath>
#include <iostream>
#include <numeric>
#include <sstream>
#include <string>
#include <vector>

/**
 * Given a string s of lower and upper case English letters.
 *
 * A good string is a string which doesn't have two adjacent characters s[i] and
 * s[i + 1] where:
 *
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case
 * or vice-versa. To make the string good, you can choose two adjacent
 * characters that make the string bad and remove them. You can keep doing this
 * until the string becomes good.
 *
 * Return the string after making it good. The answer is guaranteed to be unique
 * under the given constraints.
 *
 * Notice that an empty string is also good.
 */

std::string makeGood(std::string s) { return makeGood(0, s); }

std::string makeGood(int start, std::string s) {
  if (s.size() <= 1) return s;
  int offset = std::abs('a' - 'A');
  for (int i = start; i < s.size() - 1; ++i) {
    if (std::abs(s[i] - s[i + 1]) == offset) {
      return makeGood(i - 1 < 0 ? 0 : i - 1, s.erase(i, 2));
    }
  }
  return s;
}

// using stack
std::string makeGood2(std::string s) {
  std::string ans = "";
  ans.push_back(s[0]);

  for (int i = 1; i < s.length(); i++) {
    if (ans.length() == 0)
      ans.push_back(s[i]);
    else if ((ans[ans.length() - 1] - 32 == s[i]) ||
             (ans[ans.length() - 1] + 32 == s[i]))
      ans.pop_back();
    else if (ans[ans.length() - 1] - 32 != s[i])
      ans.push_back(s[i]);
  }

  return ans;
}