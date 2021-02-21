#include <assert.h>

#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

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

int main() {
  std::string base = "hello";
  std::string target = "ll";
  int idx = match(base, target);
  std::cout << idx << std::endl;
}