#include <algorithm>
#include <stack>
#include <string>
#include <vector>

/**
 * Given a string s which represents an expression, evaluate this expression and
 * return its value.
 *
 * The integer division should truncate toward zero.
 */
int calculate(std::string s) {
  int cur = 0;
  char op = '+';
  std::stack<int> st;
  for (int i = 0; i <= s.size(); ++i) {
    if (i < s.size() && s[i] == ' ') continue;
    if (i < s.size() && s[i] >= '0' && s[i] <= '9') {
      cur = cur * 10 + (s[i] - '0');
    } else {
      if (op == '+') {
        st.push(cur);
        cur = 0;
      } else if (op == '-') {
        st.push(-cur);
        cur = 0;
      } else if (op == '*') {
        int v = st.top();
        st.pop();
        st.push(v * cur);
        cur = 0;
      } else if (op == '/') {
        int v = st.top();
        st.pop();
        st.push(v / cur);
        cur = 0;
      }
      if (i < s.size()) op = s[i];
    }
  }
  int res = 0;
  while (!st.empty()) {
    res += st.top();
    st.pop();
  }
  return res;
}

/**
 * a more clean version without stack, just keep two number (prev and current)
 */
int calculate2(std::string s) {
  int res = 0;
  char op = '+';
  int cur = 0, prev = 0;
  for (int i = 0; i <= s.size(); ++i) {
    if (i < s.size() && s[i] == ' ') continue;
    if (i < s.size() && s[i] >= '0' && s[i] <= '9') {
      cur = cur * 10 + (s[i] - '0');
    } else {
      if (op == '+') {
        res += prev;
        prev = cur;
        cur = 0;
      } else if (op == '-') {
        res += prev;
        prev = -cur;
        cur = 0;
      } else if (op == '*') {
        prev = prev * cur;
        cur = 0;
      } else if (op == '/') {
        prev = prev / cur;
        cur = 0;
      }
      if (i < s.size()) op = s[i];
    }
  }
  res += prev;

  return res;
}