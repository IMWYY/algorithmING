# String

## KMP algorithm

Find the substr in `base` that equals to `target`.

```c++
/*
 * KMP algorihtm for pattern matching
 */
int match(std::string& base, std::string& target) {
  if (target.empty() || base.empty()) return -1;

  // next[i] is the length of longest common substr between prefix and suffix of
  // target[0:i] (0 inclusive, i exclusive)
  std::vector<int> next(target.size(), 0);
  next[0] = -1;
  int i = 0, j = -1;
  while (i < next.size() - 1) {
    if (j == -1 || target[i] == target[j]) {
      i++;
      j++;
      next[i] = j;  // init next[i]
    } else {
      j = next[j];  // keep i, and move j back to next[j]
    }
  }

  for (size_t i = 0; i < next.size(); ++i) {
    std::cout << "next[" << i << "]=" << next[i] << std::endl;
  }

  i = 0;
  j = 0;
  // note that we must convert to int for comparison, otherwise
  // -1 compare with size_t will not get expected result
  while (j < (int)target.size() && i < (int)base.size()) {
    if (j == -1 || base[i] == target[j]) {
      i++;
      j++;
    } else {
      j = next[j];
    }
  }

  if (j == target.size()) return i - j;
  return -1;
}
```

## Manacher's algorithm

Find the longest palindrome in a string `s` in O(n) time.

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

## Utils

```c++
std::vector<std::string> split(std::string s, std::string seperator) {
  std::vector<std::string> res;
  size_t start = 0;
  size_t end = s.find(seperator, start);
  while (end != std::string::npos) {
    // if (end > start) res.push_back(s.substr(start, end - start));
    res.push_back(s.substr(start, end - start));
    start = end + seperator.size();
    end = s.find(seperator, start);
  }
  if (s.size() > start) res.push_back(s.substr(start, end - start));
  return res;
}
```

## Palindromic related

- [Problem005](./Problem005.cpp)
- [Problem131](./Problem131.cpp)
- [Problem132](./Problem132.cpp)
- [Problem516](./Problem516.cpp)
- [Problem647](./Problem647.cpp)

## Subsequence related

- [Problem392](./Problem392.cpp)

## Substring related

- [Problem395](./Problem395.java)