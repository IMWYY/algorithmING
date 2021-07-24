#include <string>
#include <vector>

int minDistance(std::string word1, std::string word2) {
  if (word1 == word2) return 0;
  int m = word1.size(), n = word2.size();
  std::vector<std::vector<int>> dp(m + 1, std::vector<int>(n + 1, 0));

  for (size_t i = 0; i <= m; ++i) dp[i][0] = i;
  for (size_t j = 0; j <= n; ++j) dp[0][j] = j;

  for (size_t i = 1; i <= m; ++i) {
    for (size_t j = 1; j <= n; ++j) {
      if (word1[i - 1] == word2[j - 1])
        dp[i][j] = dp[i - 1][j - 1];
      else
        dp[i][j] =
            std::min(dp[i - 1][j], std::min(dp[i][j - 1], dp[i - 1][j - 1])) +
            1;
    }
  }

  return dp[m][n];
}