#include <algorithm>
#include <climits>
#include <cmath>
#include <iostream>
#include <numeric>
#include <string>
#include <unordered_map>
#include <vector>

/**
 * There are several stones arranged in a row, and each stone has an associated
 * value which is an integer given in the array stoneValue.
 *
 * In each round of the game, Alice divides the row into two non-empty rows
 (i.e.
 * left row and right row), then Bob calculates the value of each row which is
 the
 * sum of the values of all the stones in this row. Bob throws away the row
 which
 * has the maximum value, and Alice's score increases by the value of the
 remaining
 * row. If the value of the two rows are equal, Bob lets Alice decide which row
 * will be thrown away. The next round starts with the remaining row.
 *
 * The game ends when there is only one stone remaining. Alice's is initially
 zero.
 *
 * Return the maximum score that Alice can obtain.
 */

int dfs(std::vector<int>& prefix_sum, int start, int end,
        std::vector<std::vector<int>>& memo) {
  if (end == start) return 0;
  if (memo[start][end] > 0) return memo[start][end];
  for (int i = start + 1; i <= end; ++i) {
    int l = prefix_sum[i] - prefix_sum[start];    // [start, i)
    int r = prefix_sum[end + 1] - prefix_sum[i];  // [i, end]
    if (l < r) {
      memo[start][end] =
          std::max(memo[start][end], l + dfs(prefix_sum, start, i - 1, memo));
    } else if (l > r) {
      memo[start][end] =
          std::max(memo[start][end], r + dfs(prefix_sum, i, end, memo));
    } else {
      memo[start][end] = std::max(
          memo[start][end], l + std::max(dfs(prefix_sum, i, end, memo),
                                         dfs(prefix_sum, start, i - 1, memo)));
    }
  }
  return memo[start][end];
}

int stoneGameV(std::vector<int>& stoneValue) {
  std::vector<int> prefix_sum(stoneValue.size() + 1, 0);
  for (int i = 0; i < stoneValue.size(); ++i) {
    prefix_sum[i + 1] = prefix_sum[i] + stoneValue[i];
  }
  std::vector<std::vector<int>> memo(stoneValue.size(),
                                     std::vector<int>(stoneValue.size(), 0));
  return dfs(prefix_sum, 0, stoneValue.size() - 1, memo);
}