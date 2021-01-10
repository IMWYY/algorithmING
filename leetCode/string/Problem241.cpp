#include <string>
#include <vector>

/**
 * Given a string of numbers and operators, return all possible results from
 * computing all the different possible ways to group numbers and operators. The
 * valid operators are +, - and *.
 */
std::vector<int> diffWaysToCompute(std::string input) {
  std::vector<int> result;
  for (int i = 0; i < input.size(); ++i) {
    if (input[i] == '+' || input[i] == '-' || input[i] == '*') {
      std::vector<int> res1 = diffWaysToCompute(input.substr(0, i));
      std::vector<int> res2 =
          diffWaysToCompute(input.substr(i + 1, input.size() - i - 1));
      for (int x : res1) {
        for (int y : res2) {
          if (input[i] == '+') {
            result.push_back(x + y);
          } else if (input[i] == '-') {
            result.push_back(x - y);
          } else if (input[i] == '*') {
            result.push_back(x * y);
          }
        }
      }
    }
  }
  if (result.empty()) {
    result.push_back(std::stoi(input));
  }
  return result;
}