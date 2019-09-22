#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * You are given a string s that consists of lower case English letters and
 * brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the
 * innermost one.
 *
 * Your result should not contain any brackets.
 *
 */

// brute force. O(n^2) time complexity
string reverseParentheses(string s) {
  std::vector<int> operand;
  std::string res;
  for (int i = 0; i < s.size(); ++i) {
    if (s[i] == '(') {
      operand.push_back(res.size());
    } else if (s[i] == ')') {
      int index = operand.back();
      operand.pop_back();
      std::reverse(res.begin() + index, res.end());
    } else {
      res += s[i];
    }
  }
  return res;
}

// pretty smart solution.
// record the position of each pair and then use 
// the d to represent the direction when construct the result string
// O(n) time complexity  + O(n) space complexit
string reverseParentheses(string s) {
  std::vector<int> pair(s.size(), 0);
  std::vector<int> operand;
  std::string res;
  for (int i = 0; i < s.size(); ++i) {
    if (s[i] == '(') {
      operand.push_back(i);
    } else if (s[i] == ')') {
      int index = operand.back();
      operand.pop_back();
      pair[index] = i;
      pair[i] = index;
    }
  }

  for (int i = 0, d = 1; i < s.size(); i += d) {
    if (s[i] == '(' || s[i] == ')') {
      i = pair[i];
      d = -d;
    } else {
      res += s[i];
    }
  }
  return res;
}