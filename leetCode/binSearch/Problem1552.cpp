#include <algorithm>
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

/**
 * In universe Earth C-137, Rick discovered a special form of magnetic force
 * between two balls if they are put in his new invented basket. Rick has n
 * empty baskets, the ith basket is at position[i], Morty has m balls and needs
 * to distribute the balls into the baskets such that the minimum magnetic force
 * between any two balls is maximum.
 *
 * Rick stated that magnetic force between two different balls at positions x
 * and y is |x - y|.
 *
 * Given the integer array position and the integer m. Return the required
 * force.
 */

bool isFeasible(std::vector<int>& position, int m, int min_gap) {
  int total_ball = 1, cur_gap = 0;
  for (int i = 1; i < position.size(); ++i) {
    cur_gap += (position[i] - position[i - 1]);
    if (cur_gap >= min_gap) {
      total_ball++;
      cur_gap = 0;
    }
  }
  return total_ball >= m;
}

// binary search to find the appropriate gap
int maxDistance(std::vector<int>& position, int m) {
  std::sort(position.begin(), position.end());
  int len = position[position.size() - 1] - position[0];
  if (m == 2) return len;
  int right = len / (m - 1) + 1, left = 1;
  int gap = right - 1;
  while (left < right - 1) {
    if (isFeasible(position, m, gap)) {
      left = gap;
    } else {
      right = gap;
    }
    gap = (left + right) / 2;
  }
  return gap;
}