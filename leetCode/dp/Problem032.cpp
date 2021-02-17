#include <stack>
#include <string>
#include <vector>

// solution 1: dynamic programming
// dp[i] represents the longest parenthese ending at i
// case 1: dp[i] = dp[i-2] + 2 if s[i] == ')' && s[i-1] == '('
// case 2: dp[i] = dp[i-1] + 2 + dp[j-dp[i-1]-2] if s[i] == ')' &&
// s[i-dp[i-1]-1] == '('
int longestValidParentheses1(std::string s) {
  if (s.size() <= 1) return 0;
  std::vector<int> dp(s.size(), 0);
  int res = 0;
  for (int j = 1; j < s.size(); ++j) {
    if (s[j] == ')' && s[j - 1] == '(')
      dp[j] = std::max(dp[j], 2 + (j >= 2 ? dp[j - 2] : 0));
    if (s[j] == ')' && j - dp[j - 1] - 1 >= 0 && s[j - dp[j - 1] - 1] == '(')
      dp[j] = std::max(
          dp[j], 2 + dp[j - 1] +
                     ((j - dp[j - 1] - 2) >= 0 ? dp[j - dp[j - 1] - 2] : 0));
    res = std::max(res, dp[j]);
  }
  return res;
}


// solution2: use stack
int longestValidParentheses2(std::string s) {
  if (s.empty()) return 0;
  std::stack<int> st;
  int result = 0, cnt = 0;

  for (int i = 0; i < s.length(); ++i) {
    char c = s[i];
    if (c == ')') {
      if (st.empty()) {
        result = std::max(result, cnt);
        cnt = 0;  // recount
      } else {
        st.pop();
        cnt += 2;
        if (st.empty()) {  // update the result when we have a valid parenthese
          result = std::max(result, cnt);
        } else {
          // the valid parenthese is in the range[st.top()+1, i]
          result = std::max(result, i - st.top());
        }
      }
    } else {
      st.push(i);
    }
  }
  return result;
}