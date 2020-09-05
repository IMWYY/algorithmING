#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * Given an array of positive integers arr,  find a pattern of length m that is
 * repeated k or more times.
 *
 * A pattern is a subarray (consecutive sub-sequence) that consists of one or
 * more values, repeated multiple times consecutively without overlapping. A
 * pattern is defined by its length and the number of repetitions.
 *
 * Return true if there exists a pattern of length m that is repeated k or more
 * times, otherwise return false.
 */

// very smart solution...
bool containsPattern(std::vector<int>& arr, int m, int k) {
  int cnt = 0;
  for (int i = 0; i + m < arr.size(); i++) {
    if (arr[i] != arr[i + m]) cnt = 0;
    cnt += (arr[i] == arr[i + m]);
    if (cnt == (k - 1) * m) return true;
  }
  return false;
}

bool containsPattern1(std::vector<int>& arr, int m, int k) {
  for (int start = 0; start <= arr.size() - m; ++start) {
    int cnt = 1;
    for (int j = start + m; j <= arr.size() - m; j += m) {
      bool found = true;
      for (int t = 0; t < m; ++t) {
        if (arr[j + t] != arr[start + t]) {
          found = false;
          break;
        }
      }
      if (!found) break;
      cnt++;
      if (cnt == k) return true;
    }
  }
  return false;
}
