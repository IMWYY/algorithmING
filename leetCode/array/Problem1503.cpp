#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;

/**
 * We have a wooden plank of the length n units. Some ants are walking on the
 * plank, each ant moves with speed 1 unit per second. Some of the ants move to
 * the left, the other move to the right.
 *
 * When two ants moving in two different directions meet at some point, they
 * change their directions and continue moving again. Assume changing directions
 * doesn't take any additional time.
 *
 * When an ant reaches one end of the plank at a time t, it falls out of the
 * plank imediately.
 *
 * Given an integer n and two integer arrays left and right, the positions of
 * the ants moving to the left and the right. Return the moment when the last
 * ant(s) fall out of the plank.
 */

int getLastMoment(int n, vector<int>& left, vector<int>& right) {
  int left_max = 0, right_min = n;
  for (size_t i = 0; i < left.size(); ++i) {
    left_max = std::max(left_max, left[i]);
  }
  for (size_t i = 0; i < right.size(); ++i) {
    right_min = std::min(right_min, right[i]);
  }
  return std::max(left_max, n - right_min);
}