package leetCode.dynamicProgramming;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 */
public class Problem072 {

	/**
	 * Dyanmic programming
	 * dp[i][j] represents the min distrance converting from word1[0..i-1] to word2[0..j-1]
	 * dp[i][j] = dp[i-1][j-1] if word1[i-1] == word2[j-1]
	 * dp[i][j] = min(dp[i-1][j-1]+1, dp[i-1][j]+1, dp[i][j-1]+1)
	 * O(mn) time + O(mn) space
	 * can be optimized to O(n) space
	 */
	public int minDistance(String word1, String word2) {
		if (word1.length() == 0 || word2.length() == 0)
			return word1.length() == 0 ? word2.length() : word1.length();

		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i <= word1.length(); ++i) dp[i][0] = i;
		for (int j = 0; j <= word2.length(); ++j) dp[0][j] = j;
		for (int i = 1; i <= word1.length(); ++i) {
			for (int j = 1; j <= word2.length(); ++j) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
				}
			}
		}
		return dp[word1.length()][word2.length()];
	}

	// optimized to O(n) space
	public int minDistance2(String word1, String word2) {
		if (word1.length() == 0 || word2.length() == 0)
			return word1.length() == 0 ? word2.length() : word1.length();

		int[] dp = new int[word1.length() + 1];
		for (int i = 0; i <= word1.length(); ++i) dp[i] = i;
		for (int j = 1; j <= word2.length(); ++j) {
			int pre = dp[0];
			dp[0] = j;
			for (int i = 1; i <= word1.length(); ++i) {
				int temp = dp[i];
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i] = pre;
				} else {
					dp[j] = Math.min(Math.min(dp[i - 1], dp[i]), pre) + 1;
				}
				pre = temp;
			}
		}
		return dp[word1.length()];
	}
}
