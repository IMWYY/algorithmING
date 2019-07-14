
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

using namespace std;

/**
 * We are given hours, a list of the number of hours worked per day for a given
 * employee.
 *
 * A day is considered to be a tiring day if and only if the number of hours
 * worked is (strictly) greater than 8.
 *
 * A well-performing interval is an interval of days for which the number of
 * tiring days is strictly larger than the number of non-tiring days.
 *
 * Return the length of the longest well-performing interval.
 */

int longestWPI(vector<int>& hours) {
  int res = 0, score = 0, n = hours.size();
  unordered_map<int, int> seen = {{0, -1}};
  for (int i = 0; i < n; ++i) {
    score += hours[i] > 8 ? 1 : -1;
    if (score > 0) {
      res = i + 1;
    } else {
      if (seen.find(score) == seen.end()) seen[score] = i;
      if (seen.find(score - 1) != seen.end())
        res = max(res, i - seen[score - 1]);
    }
  }
  return res;
}