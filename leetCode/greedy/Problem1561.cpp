#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * There are 3n piles of coins of varying size, you and your friends will take
 * piles of coins as follows:
 *
 * In each step, you will choose any 3 piles of coins (not necessarily
 * consecutive). Of your choice, Alice will pick the pile with the maximum
 * number of coins. You will pick the next pile with maximum number of coins.
 * Your friend Bob will pick the last pile. Repeat until there are no more piles
 * of coins. Given an array of integers piles where piles[i] is the number of
 * coins in the ith pile.
 *
 * Return the maximum number of coins which you can have.
 */

int maxCoins(std::vector<int>& piles) {
  std::sort(piles.begin(), piles.end());
  int n = piles.size() / 3;
  int res = 0;
  for (int i = 0; i < n; ++i) {
    res += piles[piles.size() - (2 * (i + 1))];
  }
  return res;
}