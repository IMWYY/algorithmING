
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

// very clever solution!!!
// We starts with a score = 0,
// If the working hour > 8, we plus 1 point.
// Otherwise we minus 1 point.
// We want find the maximum interval that have strict positive score.
//
// After one day of work, if we find the total score > 0,
// the whole interval has positive score,
// so we set res = i + 1.
//
// If the score is a new lowest score, we record the day by seen[cur] = i.
// seen[score] means the first time we see the score is seen[score]th day.
//
// We want a positive score, so we want to know the first occurrence of score
// - 1. score - x also works, but it comes later than score - 1. So the maximum
// interval is i - seen[score - 1]
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
        // the interval [seen[score - 1], i) or (seen[score - 1], i] is a
        // well-performing interval
        res = max(res, i - seen[score - 1]);
    }
  }
  return res;
}