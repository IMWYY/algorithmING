#include <string>
#include <unordered_map>
#include <unordered_set>

// hashmap + two pointer
// O(n) time + O(n) space
int lengthOfLongestSubstring1(std::string s) {
  std::unordered_map<char, int> mp;
  int res = 0;
  for (int start = 0, end = 0; end < s.size(); ++end) {
    if (mp.count(s[end]) && mp[s[end]] >= start) start = mp[s[end]] + 1;
    mp[s[end]] = end;
    res = std::max(res, end - start + 1);
  }
  return res;
}

// hashset + two pointer
// O(n^2) time + O(n) space
int lengthOfLongestSubstring2(std::string s) {
  std::unordered_set<char> memo;
  int res = 0;
  for (int start = 0, end = 0; end < s.size(); ++end) {
    while (start < end && memo.count(s[end])) {
      memo.erase(s[start++]);
    }
    memo.insert(s[end]);
    res = std::max(res, end - start + 1);
  }
  return res;
}