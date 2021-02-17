#include <string>
#include <vector>
std::vector<std::vector<std::string>> partition(std::string s) {
  std::vector<std::vector<std::string>> ret;
  if (s.empty()) return ret;
  std::vector<std::string> path;
  backtrack(ret, path, s, 0);
  return ret;
}

void backtrack(std::vector<std::vector<std::string>>& ret,
               std::vector<std::string>& path, std::string& s, int index) {
  if (index == s.size())
    ret.push_back(path);
  else {
    for (int i = index; i < s.size(); ++i) {
      if (isPalindrome(s, index, i)) {
        path.push_back(s.substr(index, i - index + 1));
        backtrack(ret, path, s, i + 1);
        path.pop_back();
      }
    }
  }
}

bool isPalindrome(const std::string& s, int start, int end) {
  while (start <= end) {
    if (s[start++] != s[end--]) return false;
  }
  return true;
}