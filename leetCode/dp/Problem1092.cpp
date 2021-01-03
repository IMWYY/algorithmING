
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * Given two strings str1 and str2, return the shortest string that has both
 * str1 and str2 as subsequences.  If multiple answers exist, you may return any
 * of them.
 *
 * (A string S is a subsequence of string T if deleting some number of
 * characters
 * from T (possibly 0, and the characters are chosen anywhere from T) results in
 * the string S.)
 */

/**
 * dynamic programming to get the longest common subsequence of
 * two string and then insert the remaining chars to the longer string
 * at specfic positions.
 */
string shortestCommonSupersequence(string str1, string str2) {
  // ensure str1.size() > str2.size()
  if (str1.size() < str2.size()) {
    std::string temp = str2;
    str2 = str1;
    str1 = temp;
  }
  std::vector<std::vector<int>> dp(str1.size() + 1,
                                   std::vector<int>(str2.size() + 1, 0));
  // indexs1 and indexs2 contains the postions of subsequence of two str
  std::vector<int> indexs1, indexs2;
  for (size_t i = 1; i <= str1.size(); ++i) {
    for (size_t j = 1; j <= str2.size(); ++j) {
      if (str1[i - 1] == str2[j - 1]) {
        dp[i][j] = dp[i - 1][j - 1] + 1;
      } else {
        dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
  }

  for (size_t i = str1.size(), j = str2.size(); i >= 1 && j >= 1;) {
    if (str1[i - 1] == str2[j - 1]) {
      indexs1.push_back(i - 1);
      indexs2.push_back(j - 1);
      i--;
      j--;
    } else {
      if (dp[i - 1][j] >= dp[i][j - 1])
        i--;
      else
        j--;
    }
  }

  std::string res = str1;
  std::vector<int> pos_mapping(str2.size(), -1);

  // pos_mapping contains the corresponding insert postion in str1
  for (int i = 0; i < indexs2.size(); i++) {
    pos_mapping[indexs2[i]] = indexs1[i];
  }

  int cur_pos = str1.size();
  // note here we need to insert in reverse order
  for (int i = pos_mapping.size() - 1; i >= 0; i--) {
    if (pos_mapping[i] >= 0) {
      cur_pos = pos_mapping[i];
    } else {
      res.insert(cur_pos, 1, str2[i]);
    }
  }

  return res;
}