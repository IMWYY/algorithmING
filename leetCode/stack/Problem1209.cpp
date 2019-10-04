#include <algorithm>
#include <array>
#include <queue>
#include <string>
#include <unordered_map>
#include <vector>
using namespace std;

// solution 1:
// use stack to record current char and the
// adjacent length of the char
string removeDuplicates(string s, int k) {
  vector<pair<int, char>> stack = {{0, '#'}};
  for (char c : s) {
    if (stack.back().second != c) {
      stack.push_back({1, c});
    } else if (++stack.back().first == k)
      stack.pop_back();
  }
  string res;
  for (auto& p : stack) {
    res += string(p.first, p.second);
  }
  return res;
}

// solution 2:
// use count[] to record the adjacent length of
// character at s[i]. when remove the adjacent characters,
// roll back the index.
// this solution is so smart!
string removeDuplicates(string s, int k) {
  int i = 0, n = s.length();
  vector<int> count(n);
  for (int j = 0; j < n; ++j, ++i) {
    s[i] = s[j];
    count[i] = i > 0 && s[i - 1] == s[j] ? count[i - 1] + 1 : 1;
    if (count[i] == k) i -= k;
  }
  return s.substr(0, i);
}