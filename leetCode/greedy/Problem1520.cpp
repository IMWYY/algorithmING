#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * Given a string s of lowercase letters, you need to find the maximum number of
 * non-empty substrings of s that meet the following conditions:
 *
 * The substrings do not overlap, that is for any two substrings s[i..j] and
 * s[k..l], either j < k or i > l is true. A substring that contains a certain
 * character c must also contain all occurrences of c. Find the maximum number
 * of substrings that meet the above conditions. If there are multiple solutions
 * with the same number of substrings, return the one with minimum total length.
 * It can be shown that there exists a unique solution of minimum total length.
 *
 * Notice that you can return the substrings in any order.
 */

int valid_str(std::vector<std::vector<int>> &ranges, string &s, int i) {
  int right = ranges[s[i] - 'a'][1], left = ranges[s[i] - 'a'][0];
  for (int j = i; j <= right; ++j) {
    if (ranges[s[j] - 'a'][0] < i)
      return -1;  // there is a substring that starts before this
    right = std::max(right,
                     ranges[s[j] - 'a'][1]);  // the right idx that covers this
  }
  return right;
}

vector<string> maxNumOfSubstrings(string s) {
  std::vector<std::vector<int>> ranges(26, std::vector<int>(2, -1));
  for (int i = 0; i < s.size(); ++i) {
    int idx = s[i] - 'a';
    if (ranges[idx][0] == -1) {  // left idx
      ranges[idx][0] = i;
      ranges[idx][1] = i;
    } else {  // right idx
      ranges[idx][1] = i;
    }
  }

  std::vector<string> res;
  int pre_right = -1;
  for (int i = 0; i < s.size(); ++i) {
    int idx = s[i] - 'a';
    if (ranges[idx][0] == i) {
      int new_right = valid_str(ranges, s, i);
      if (new_right == -1) continue;  // this is not a valid substring
      if (i > pre_right)
        res.push_back(
            "");  // there is no overlapped area, we can create a new substring
      pre_right = new_right;
      res.back() = s.substr(i, new_right - i + 1);  // update the latest substring
    }
  }
  return res;
}