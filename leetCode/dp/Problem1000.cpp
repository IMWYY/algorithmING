#include <climits>
#include <numeric>
#include <vector>

/**
 * State: Minimum cost merging piles from i to j to k pile.
 *
 * Function:
 * dp[i][j][1] = dp[i][j][K] + sum[i][j] (dp[i][j][K] != max)
 * dp[i][j][k] = min(dp[i][t][k - 1] + dp[t + 1][j][1]) (t ∈ [i, j) &&
 *          dp[i][t][k - 1] != max && dp[t+1][j][1] != max && k ∈ [2, K])
 *
 * Init: dp[i][i][1] = 0 (Already a pile) others = max
 *
 * Answer: dp[1][len][1] (len is the stones number)
 *
 * Time Complexity: O(n^3 * K) (n is the number of stone)
 */
int mergeStones(std::vector<int>& stones, int K) {
  int n = stones.size();
  if (n == 1) return 0;
  if (K <= 0 || K > stones.size()) return -1;
  if (((n - K) % (K - 1)) != 0) return -1;
  if (K == 1 || K == stones.size())
    return std::accumulate(stones.begin(), stones.end(), 0);

  std::vector<std::vector<std::vector<int>>> dp(
      n, std::vector<std::vector<int>>(n, std::vector<int>(K, INT_MAX)));
  std::vector<int> pre_sum(n + 1, 0);
  for (int i = 1; i <= n; ++i) pre_sum[i] += (stones[i - 1] + pre_sum[i - 1]);

  for (int i = n - 1; i >= 0; i--) {
    for (int j = i; j < n; ++j) {
      int sum = pre_sum[j + 1] - pre_sum[i];
      if (j - i < K)
        dp[i][j][j - i] = 0;  // no cost to merge [i, j] into [j-i + 1] piles

      for (int k = 1; k < K; k++) {
        for (int t = i; t < j; ++t) {
          if (dp[i][t][0] == INT_MAX || dp[t + 1][j][k - 1] == INT_MAX)
            continue;
          dp[i][j][k] =
              std::min(dp[i][j][k], dp[i][t][0] + dp[t + 1][j][k - 1]);
        }
      }
      if (dp[i][j][K - 1] == INT_MAX) continue;
      dp[i][j][0] = dp[i][j][K - 1] + sum;
    }
  }
  return dp[0][n - 1][0];
}