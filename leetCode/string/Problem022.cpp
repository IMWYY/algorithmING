#include <string>
#include <vector>

void gen(std::vector<std::string>&, std::string, int, int, int, int);

std::vector<std::string> generateParenthesis(int n) {
  std::vector<std::string> result;
  gen(result, "", n, 0, 0, 0);
  return result;
}

void gen(std::vector<std::string>& res, std::string cur, int n, int left,
         int right, int cnt) {
  if (cnt < 0) return;
  if (left == n && right == n) {
    res.push_back(cur);
    return;
  }
  if (right < n) {
    gen(res, cur + ')', n, left, right + 1, cnt - 1);
  }
  if (left < n) {
    gen(res, cur + '(', n, left + 1, right, cnt + 1);
  }
}