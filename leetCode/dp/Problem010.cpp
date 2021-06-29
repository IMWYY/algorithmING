#include <string>
#include <vector>

/**
 * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
 * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
 * 3, If p.charAt(j) == '*':
 *    here are two sub conditions:
 *    3.1 if p.charAt(j-1) != s.charAt(i) :
 *      dp[i][j] = dp[i][j-2]
 *      //in this case, a* only counts as empty
 *    3.2 if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) == '.':
 *      dp[i][j] = dp[i-1][j]
 *      //in this case, a* counts as multiple a or dp[i][j] = dp[i][j-2]
 */
bool isMatch(std::string s, std::string p) {
  if (p.empty()) return s.empty();
  std::vector<std::vector<bool>> dp(s.size() + 1,
                                    std::vector<bool>(p.size() + 1, false));
  dp[0][0] = true;
  for (int i = 0; i < p.size(); i++) {
    if (p[i] == '*' && dp[0][i - 1]) {
      dp[0][i + 1] = true;
    }
  }
  for (int i = 0; i < s.size(); i++) {
    for (int j = 0; j < p.size(); j++) {
      if (p[j] == '.' || p[j] == s[i]) {
        dp[i + 1][j + 1] = dp[i][j];
      } else if (p[j] == '*') {
        if (p[j - 1] != s[i] && p[j - 1] != '.') {
          dp[i + 1][j + 1] = dp[i + 1][j - 1];  // skip .*
          dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j - 1];
        }
      }
    }
  }
  return dp[s.size()][p.size()];
}