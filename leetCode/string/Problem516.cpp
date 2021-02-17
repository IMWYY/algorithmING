#include <vector>
#include <string>

// dynamic programming
// dp[i][j] = dp[i+1][j-1] if s[i] == s[j]
// dp[i][j] = max(dp[i+1][j], dp[i][j-1]) otherwise
int longestPalindromeSubseq(std::string s) {
  std::vector<std::vector<int>> dp(s.size(), std::vector<int>(s.size(), 0));
  for (int j = 0; j < s.size(); ++j) {
    dp[j][j] = 1;
    for (int i = j - 1; i >= 0; i--) {
      if (s[i] == s[j]) {
        dp[i][j] = dp[i + 1][j - 1] + 2;
      } else {
        dp[i][j] = std::max(dp[i + 1][j], dp[i][j - 1]);
      }
    }
  }
  return dp[0][s.size() - 1];
}