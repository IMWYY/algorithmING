#include <climits>
#include <string>
#include <unordered_map>

std::string minWindow(std::string s, std::string t) {
  std::unordered_map<int, int> mp;
  int total = 0;
  for (int i = 0; i < t.size(); ++i) {
    if (mp[t[i]] == 0) total++;
    mp[t[i]]++;
  }

  std::unordered_map<int, int> counter;
  int curcnt = 0, head = -1, len = INT_MAX;
  for (int start = 0, end = 0; end < s.size(); end++) {
    if (!mp[s[end]]) continue;
    // increase counter
    counter[s[end]]++;
    if (counter[s[end]] == mp[s[end]]) curcnt++;

    while (curcnt == total) { // check condition
      if (end - start + 1 < len) {
        len = end - start + 1;
        head = start;
      }
      if (mp[s[start]]) { // update the minimum
        counter[s[start]]--;
        if (counter[s[start]] < mp[s[start]]) curcnt--;
      }
      start++;
    }
  }
  return head >= 0 ? s.substr(head, len) : "";
}