#include <string>
#include <vector>

// solution1: just keep two pointers
bool isSubsequence(std::string s, std::string t) {
  int sLen = s.length(), sIdx = 0, tLen = t.length();
  for (int i = 0; i < tLen && sIdx < sLen; i++)
    if (t[i] == s[sIdx]) sIdx++;
  return sIdx == sLen;
}

// solution 2:
// record the indexes of each char in t,
// then traverse s, check if there exists an increasing indexes sequences.
bool isSubsequence1(std::string s, std::string t) {
  if (s.length() == 0) return true;
  std::vector<std::vector<int>> mp(26, std::vector<int>());
  for (int i = 0; i < t.length(); ++i) {
    mp[t[i] - 'a'].push_back(i);
  }

  int lastIndex = -1;
  for (char c : s) {
    if (mp[c - 'a'].size() == 0) return false;
    auto it =
        std::lower_bound(mp[c - 'a'].begin(), mp[c - 'a'].end(), lastIndex + 1);
    if (it == mp[c - 'a'].end()) return false;
    lastIndex = mp[c - 'a'][it - mp[c - 'a'].begin()];
  }
  return true;
}