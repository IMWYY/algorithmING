
#include <algorithm>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 */

void helper(std::string& num, int target, std::vector<std::string>& res,
            std::string current, int pos, int64_t eval, int64_t multed) {
  if (pos == num.size()) {
    if (eval == target) {
      res.push_back(current);
    }
    return;
  }

  for (int i = pos; i < num.size(); ++i) {
    if (i > pos && num[pos] == '0') break;
    int64_t now = std::stol(num.substr(pos, i - pos + 1));

    helper(num, target, res, current + '+' + num.substr(pos, i - pos + 1),
           i + 1, eval + now, now);
    helper(num, target, res, current + '-' + num.substr(pos, i - pos + 1),
           i + 1, eval - now, -now);
    helper(num, target, res, current + '*' + num.substr(pos, i - pos + 1),
           i + 1, eval - multed + now * multed, now * multed);
  }
}

std::vector<std::string> addOperators(std::string num, int target) {
  std::vector<std::string> res;
  if (num.size() == 0) return res;

  for (int i = 0; i < num.size(); ++i) {
    if (i > 0 && num[0] == '0') break;
    int64_t eval = std::stol(num.substr(0, i + 1));
    helper(num, target, res, num.substr(0, i + 1), i + 1, eval, eval);
  }

  return res;
}