
#include <algorithm>
#include <stack>
#include <string>
#include <vector>

int parse_num(std::string& s, int start, int& end);

int parse_expr(std::string& s, int start, int& end);

int calculate(std::string& s) {
  int i;
  return parse_expr(s, 0, i);
}

int parse_num(std::string& s, int start, int& end) {
  int num = 0;
  for (int i = start; i < s.size(); ++i) {
    if (s[i] == ' ') continue;
    if (s[i] >= '0' && s[i] <= '9') {
      num = num * 10 + (s[i] - '0');
    } else {
      end = i;
      break;
    }
  }
  return num;
}

int parse_expr(std::string& s, int start, int& end) {
  char op = '+';
  int cur = 0;
  std::stack<int> st;
  for (int i = start; i < s.size(); ++i) {
    if (s[i] == ' ') continue;

    if (s[i] >= '0' && s[i] <= '9') {
      cur = parse_num(s, i, i);
    } else if (s[i] == '(') {
      cur = parse_expr(s, i + 1, i);
    }

    if (op == '+') {
      st.push(cur);
      cur = 0;
    } else if (op == '-') {
      st.push(-cur);
      cur = 0;
    } else if (op == '*') {
      cur = st.top() * cur;
      st.pop();
      st.push(cur);
      cur = 0;
    } else if (op == '/') {
      cur = st.top() / cur;
      st.pop();
      st.push(cur);
      cur = 0;
    }
    op = s[i];

    if (s[i] == ')') {  // simply return upon a ')'
      end = i + 1;
      break;
    }
  }

  int res = 0;
  while (!st.empty()) {
    res += st.top();
    st.pop();
  }
  return res;
}
