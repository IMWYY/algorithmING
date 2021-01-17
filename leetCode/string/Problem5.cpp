#include <string>
#include <vector>

/**
 * Given a string s, return the longest palindromic substring in s.
 */

// manacher algorithm
std::string longestPalindrome(std::string s) {
  if (s.size() <= 1) return s;
  std::string str = "^#";
  for (size_t i = 0; i < s.size(); ++i) {
    str += s[i];
    str += '#';
  }
  str += '$';

  int n = str.size();
  std::vector<int> p(n, 0);
  int c = 0, r = c + p[c];
  for (int i = 1; i < n; ++i) {
    int mirror = 2 * c - i;
    if (r > i) {
      p[i] = std::min(r - i, p[mirror]);
    } else {
      p[i] = 0;
    }

    while (str[i - 1 - p[i]] == str[i + 1 + p[i]]) {
      p[i]++;
    }

    if (i + p[i] > r) {
      c = i;
      r = i + p[i];
    }
  }

  int max_len = 0;
  int center_idx = 0;
  for (int i = 0; i < n; ++i) {
    if (p[i] > max_len) {
      max_len = p[i];
      center_idx = i;
    }
  }
  int start = (center_idx - 1) / 2 - max_len / 2;
  return s.substr(start, max_len);
}