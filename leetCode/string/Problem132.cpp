#include <string>
#include <vector>

// dynamic programming
// parlin[i][j] is true when s[i,j] is a parlindrome
// parlin[i][j] = true if s[i] == s[j] && dp[i+1][j-1]
//
// dp[i] is the minimum cut need to cut s[:i]
// dp[i] = min(i-1, dp[j] + 1) for each j that satisfy s[j,i] is a parlindrome
int minCut(std::string s) {
  std::vector<std::vector<bool>> parlin(s.size(),
                                        std::vector<bool>(s.size(), false));

  // get all palindrome
  for (int j = 0; j < s.size(); ++j) {
    for (int i = 0; i <= j; ++i) {
      if (s[i] == s[j] && (i + 1 >= j - 1 || parlin[i + 1][j - 1])) {
        parlin[i][j] = true;
      }
    }
  }

  // dynamic programming
  std::vector<int> dp(s.size() + 1, 0);
  for (int i = 0; i < dp.size(); ++i)
    dp[i] = i - 1;  // dp[0] must be -1 to embrace the corner case

  for (int j = 1; j <= s.size(); ++j) {
    for (int i = 0; i < j; ++i) {
      if (parlin[i][j - 1]) {
        dp[j] = std::min(dp[j], dp[i] + 1);
      }
    }
  }
  return dp[s.size()];
}