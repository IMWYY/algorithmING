#include <algorithm>
#include <stack>
#include <string>
#include <vector>

/**
 * Given a string s representing an expression, implement a basic calculator to
 * evaluate it.
 */
int calculate(std::string s) {
  int res = 0;
  int sign = 1;
  int cur = 0;
  std::stack<int> st;
  for (int i = 0; i < s.size(); ++i) {
    if (s[i] == ' ') continue;
    if (s[i] >= '0' && s[i] <= '9') {
      cur = cur * 10 + (s[i] - '0');
    } else if (s[i] == '(') {
      st.push(res);  // push current res and sign into stack, just start another
                     // round
      st.push(sign);
      res = 0;
      sign = 1;
    } else if (s[i] == ')') {
      res += sign * cur;
      cur = 0;
      int s = st.top();
      st.pop();
      res *= s;
      int v = st.top();
      st.pop();
      res += v;
    } else {
      res += sign * cur;
      cur = 0;
      sign = s[i] == '+' ? 1 : -1;
    }
  }
  res += sign * cur;
  return res;
}