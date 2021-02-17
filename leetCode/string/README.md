# String

### Manacher's algorithm

Find the longest palindrome in O(n) time.

```c++
std::string longestPalindrome(std::string s) {
  if (s.size() <= 1) return s;
  std::string str = "^#";
  for (size_t i = 0; i < s.size(); ++i) {
    str += s[i];
    str += '#';
  }
  str += '$';

  int n = str.size();
  std::vector<int> p(n, 0);
  int center = 0, right_most = center + p[center];
  for (int i = 1; i < n; ++i) {
    int left_mirror_i = 2 * center - i;
    if (right_most > i) {
      p[i] = std::min(right_most - i, p[left_mirror_i]);
    } else {  // i exceeds the control range of center
      p[i] = 0;
    }

    while (str[i - 1 - p[i]] == str[i + 1 + p[i]]) {
      p[i]++;
    }

    if (i + p[i] > right_most) {
      center = i;  // update center
      right_most = i + p[i]; // update right_most
    }
  }

  // get the max length
  int max_len = 0;
  int center_idx = 0;
  for (int i = 0; i < n; ++i) {
    if (p[i] > max_len) {
      max_len = p[i];
      center_idx = i;
    }
  }
  int start = (center_idx - 1) / 2 - max_len / 2;
  return s.substr(start, max_len);
}
```