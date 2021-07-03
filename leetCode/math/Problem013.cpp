#include <string>
#include <unordered_map>

int romanToInt(std::string s) {
  int res = 0;
  std::unordered_map<char, int> mp = {{'I', 1},   {'V', 5},   {'X', 10},
                                      {'L', 50},  {'C', 100}, {'D', 500},
                                      {'M', 1000}};
  for (int i = 0; i < s.size(); ++i) {
    res += mp[s[i]];
    if (i > 0 && mp[s[i - 1]] < mp[s[i]]) {
      res -= 2 * mp[s[i - 1]];
    }
  }
  return res;
}