#include <string>
#include <vector>

std::vector<std::string> mp = {"",    "",    "abc",  "def", "ghi",
                               "jkl", "mno", "pqrs", "tuv", "wxyz"};

// depth-first solution
void dfs(std::string&, int, std::string&, std::vector<std::string>&);
std::vector<std::string> letterCombinations1(std::string digits) {
  std::vector<std::string> res;
  std::string cur;
  dfs(digits, 0, cur, res);
  return res;
}

void dfs(std::string& digits, int idx, std::string& cur,
         std::vector<std::string>& res) {
  if (idx == digits.size()) {
    if (!cur.empty()) res.push_back(cur);
    return;
  }
  for (int i = 0; i < mp[digits[idx] - '0'].size(); ++i) {
    cur += mp[digits[idx] - '0'][i];
    dfs(digits, idx + 1, cur, res);
    cur.pop_back();
  }
}

// iterative solution
std::vector<std::string> letterCombinations2(std::string digits) {
  if (digits.empty()) return {};
  std::vector<std::string> result;
  result.push_back("");
  for (auto digit : digits) {
    std::vector<std::string> tmp;
    for (auto candidate : mp[digit - '0']) {
      for (auto s : result) {
        tmp.push_back(s + candidate);
      }
    }
    result.swap(tmp);
  }
  return result;
}