//
// Created by 王友运 on 2019-03-04.
//
#include <vector>
#include <numeric>
#include <iostream>

using namespace std;

/**
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 * A move consists of merging exactly K consecutive piles into one pile, and the cost of this move
 * is equal to the total number of stones in these K piles.
 * Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 *
 * Example 1:
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 */
int mergeStones(vector<int> &stones, int K) {
    if (stones.size() == 1) return 0;
    if (K == 0 || stones.size() < K) return -1;
    if (K == 1) return accumulate(stones.begin(), stones.end(), 0);
    if ((stones.size() - K) % (K - 1) != 0) return -1;

    int dp[stones.size()][stones.size()][K + 1];
    int prefix_sum[stones.size() + 1];
    fill(dp[0][0], dp[0][0] + stones.size() * stones.size() * (K + 1), INT_MAX);
    fill(prefix_sum, prefix_sum + stones.size() + 1, 0);

    for (int i = 0; i < stones.size(); ++i) {
        dp[i][i][1] = 0;
        prefix_sum[i + 1] = prefix_sum[i] + stones[i];
    }

    // dp[i][j][k] = min(dp[i][t][k-1] + dp[t+1][j][1]) for t in (i, j)
    // 注意INT_MAX是有影响的
    for (int l = 2; l <= stones.size(); ++l) {
        for (int i = 0; i <= stones.size() - l; ++i) {
            int j = i + l - 1;
            for (int k = 2; k <= K; ++k) {
                for (int t = i; t < j; ++t) {
                    if (dp[i][t][k - 1] == INT_MAX || dp[t + 1][j][1] == INT_MAX) {
                        continue;
                    }
                    dp[i][j][k] = min(dp[i][j][k], dp[i][t][k - 1] + dp[t + 1][j][1]);
                }
            }
            if (dp[i][j][K] == INT_MAX) {
                continue;
            }
            dp[i][j][1] = dp[i][j][K] + prefix_sum[j + 1] - prefix_sum[i];
        }
    }

    return dp[0][stones.size() - 1][1];
}

int main() {
    vector<int> A{4, 6, 4, 7, 5};
    cout << mergeStones(A, 2) << endl;
}
