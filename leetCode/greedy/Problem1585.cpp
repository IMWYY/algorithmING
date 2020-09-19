#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 *
 * Given two strings s and t, you want to transform string s into string t using
 * the following operation any number of times:
 *
 * Choose a non-empty substring in s and sort it in-place so the characters are
 * in ascending order. For example, applying the operation on the underlined
 * substring in "14234" results in "12344".
 *
 * Return true if it is possible to transform string s into string t. Otherwise,
 * return false.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 */

bool isTransformable(std::string s, std::string t) {
  std::vector<std::vector<int>> index(10, std::vector<int>());
  for (int i = s.size() - 1; i >= 0; --i) {
    index[s[i] - '0'].push_back(i);
  }

  // for each char ch in target, check whether we can place ch at the
  // corresponding position from source
  for (char ch : t) {
    int key = ch - '0';
    if (index[key].empty())
      return false;  // more characters than souces, return false
    for (int k = 0; k < key; ++k) {
      // k is smaller than key, we cannot move key to the front of k, return
      // false
      if (index[k].size() && index[k].back() < index[key].back()) return false;
    }
    index[key].pop_back();  // the char at this index has been used, pop it out.
  }
  return true;
}