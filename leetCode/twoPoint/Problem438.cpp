#include <string>
#include <unordered_map>
#include <vector>

/**
 * sliding window
 */
std::vector<int> findAnagrams(std::string s, std::string p) {
  std::unordered_map<int, int> mp;
  for (char c : p) mp[c - 'a']++;
  int total = mp.size();
  int cnt = 0;
  std::vector<int> res;
  for (int start = 0, end = 0; end < s.size(); end++) {
    if (mp.count(s[end] - 'a') > 0) {
      if (--mp[s[end] - 'a'] == 0) cnt++;
    }
    while (cnt == total) {
      if (end - start + 1 == p.size()) res.push_back(start);
      if (mp.count(s[start] - 'a') > 0) {
        if (mp[s[start] - 'a']++ == 0) cnt--;
      }
      start++;
    }
  }
  return res;
}