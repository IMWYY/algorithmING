#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * Given an integer n and an integer array rounds. We have a circular track
 * which consists of n sectors labeled from 1 to n. A marathon will be held on
 * this track, the marathon consists of m rounds. The ith round starts at sector
 * rounds[i - 1] and ends at sector rounds[i]. For example, round 1 starts at
 * sector rounds[0] and ends at sector rounds[1]
 *
 * Return an array of the most visited sectors sorted in ascending order.
 *
 * Notice that you circulate the track in ascending order of sector numbers in
 * the counter-clockwise direction (See the first example)
 */

std::vector<int> mostVisited(int n, std::vector<int>& rounds) {
  std::vector<int> sector(n, 0);
  for (int i = 0; i < rounds.size() - 1; ++i) {
    int start = rounds[i], end = rounds[i + 1];
    for (int j = start; j < (end < start ? (end + n) : end); ++j) {
      sector[(j % n == 0 ? n : j % n) - 1]++;
    }
  }
  sector[rounds[rounds.size() - 1] - 1]++;
  std::vector<int> res;
  int max = -1;
  for (int i = 0; i < sector.size(); ++i) {
    if (sector[i] > max) {
      res.clear();
      res.push_back(i + 1);
      max = sector[i];
    } else if (sector[i] == max) {
      res.push_back(i + 1);
    }
  }
  return res;
}