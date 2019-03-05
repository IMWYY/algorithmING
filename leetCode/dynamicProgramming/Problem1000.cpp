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
/**
 * 3D DP
 * dp[i][j][m] means the cost needed to merge stone[i] ~ stones[j] into m piles.
 *
 * Initial status dp[i][i][1] = 0 and dp[i][i][m] = infinity
 * dp[i][j][1] = dp[i][j][k] + stonesNumber[i][j]
 * dp[i][j][m] = min(dp[i][mid][1] + dp[mid + 1][j][m - 1]
 *
 * Time O(N^3/K), Space O(KN^2)
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

    // dp[i][j][k] 是指i到j合并成k个元素
    // dp[i][j][k] = min(dp[i][t][k-1] + dp[t+1][j][1]) for t in (i, j)
    //
    // 注意dp[i][j][k]=INT_MAX是指i->j合并到k是不可能的 所以需要忽略所有的INT_MAX
    //
    // 同时需要注意 这里动态规划最外层循环是step size 从2到k 否则不能正确初始化
    for (int l = 2; l <= stones.size(); ++l) {
        for (int i = 0; i + l <= stones.size(); ++i) {
            int j = i + l - 1;
            for (int k = 2; k <= K; ++k) {
                for (int t = i; t < j; ++t) {
                    if (dp[i][t][k - 1] == INT_MAX || dp[t + 1][j][1] == INT_MAX) continue;
                    dp[i][j][k] = min(dp[i][j][k], dp[i][t][k - 1] + dp[t + 1][j][1]);
                }
            }
            if (dp[i][j][K] == INT_MAX) continue;
            // k=1的情况需要最后初始化
            dp[i][j][1] = dp[i][j][K] + prefix_sum[j + 1] - prefix_sum[i];
        }
    }

    return dp[0][stones.size() - 1][1];
}

/**
 * 2D dp
 * stones[i] ~ stones[j] will merge as much as possible.
 * Finally (j - i) % (K - 1) + 1 piles will be left.
 * It's less than K piles and no more merger happens.
 * dp[i][j] means the minimum cost needed to merge stones[i] ~ stones[j].
 *
 * Time O(N^3/K) Space O(N^2)
 */
int mergeStones1(vector<int> &stones, int K) {
    int n = stones.size();
    if ((n - 1) % (K - 1)) return -1;

    vector<int> prefix(n + 1);
    for (int i = 0; i < n; i++)
        prefix[i + 1] = prefix[i] + stones[i];

    vector<vector<int> > dp(n, vector<int>(n, 0));
    for (int m = K; m <= n; ++m) {
        for (int i = 0; i + m <= n; ++i) {
            int j = i + m - 1;
            dp[i][j] = INT_MAX;
            for (int mid = i; mid < j; mid += K - 1) {
                if (dp[i][mid] == INT_MAX || dp[mid + 1][j] == INT_MAX) continue;
                dp[i][j] = min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
            }
            if ((j - i) % (K - 1) == 0)
                dp[i][j] += prefix[j + 1] - prefix[i];
        }
    }
    return dp[0][n - 1];
}

int main() {
    vector<int> A{4, 6, 4, 7, 5};
    cout << mergeStones(A, 2) << endl;
    cout << ~1 << endl;
}
