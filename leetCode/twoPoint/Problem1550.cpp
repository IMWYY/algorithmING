#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

/**
 * Given an integer array arr, return true if there are three consecutive odd
 * numbers in the array. Otherwise, return false.
 *
 * Example 1:
 *
 * Input: arr = [2,6,4,1]
 * Output: false
 * Explanation: There are no three consecutive odds.
 * Example 2:
 *
 * Input: arr = [1,2,34,3,4,5,7,23,12]
 * Output: true
 * Explanation: [5,7,23] are three consecutive odds.
 */

bool threeConsecutiveOdds(std::vector<int>& arr) {
  int start = 0, end = 0;
  while (end < arr.size()) {
    if (arr[end] % 2 == 1) {
      end++;
      if (end - start == 3) return true;
    } else {
      start = end + 1;
      end = start;
    }
  }
  return false;
}