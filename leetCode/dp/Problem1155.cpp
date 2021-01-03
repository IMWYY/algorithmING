#include <bitset>
#include <string>
#include <vector>

using namespace std;

/**
 * Given a string text, we are allowed to swap two of the characters in the
 * string. Find the length of the longest substring with repeated characters.
 */

/**
 * recursive with memorizationo
 * we store there res + 1 to check if the result has been pre-computed
 */
int dp[31][1001] = {};
int numRollsToTarget(int d, int f, int target) {
  if (d == 0 || target <= 0) return d == target;
  if (dp[d][target]) return dp[d][target] - 1;
  int res = 0;
  for (auto i = 1; i <= f; ++i)
    res = (res + numRollsToTarget(d - 1, f, target - i)) % 1000000007;
  dp[d][target] = res + 1;
  return res;
}