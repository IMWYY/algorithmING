#include <string>
#include <vector>

// solution 1: kmp algorithm
int strStr(std::string haystack, std::string needle) {
  if (needle.size() == 0) return 0;
  std::vector<int> next(needle.size(), 0);
  next[0] = -1;
  int i = 0, j = -1;
  while (i < (int)needle.size() - 1) {
    if (j == -1 || needle[i] == needle[j]) {
      ++i;
      ++j;
      next[i] = j;
    } else {
      j = next[j];
    }
  }

  i = 0;
  j = 0;
  // note that we must convert to int for comparison, otherwise
  // -1 compare with size_t will not get expected result
  while (i < (int)haystack.size() && j < (int)needle.size()) {
    if (j == -1 || haystack[i] == needle[j]) {
      ++i;
      ++j;
    } else {
      j = next[j];
    }
  }
  if (j == needle.size()) return i - j;
  return -1;
}

// solution2: two pointers
int strStr2(std::string haystack, std::string needle) {
  for (int i = 0;; i++) {
    for (int j = 0;; j++) {
      if (j == needle.size()) return i;
      if (i + j == haystack.size()) return -1;
      if (needle[j] != haystack[i + j]) break;
    }
  }
}