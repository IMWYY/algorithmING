package leetCode.dynamicProgramming;

/**
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * You have the following 3 operations permitted on a word:
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * Example 1:
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * <p>
 * Example 2:
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * <p>
 * create by stephen on 2018/5/20
 */
public class Problem072 {

	/**
	 * 动态规划
	 * dp[i][j] 表示从word1[0..i-1]到word2[0..j-1]需要的最小步数
	 * 除了边界条件
	 * dp[i][j] = dp[i-1][j-1] if word1[i-1] == word2[j-1]
	 * dp[i][j] = min(dp[i-1][j-1]+1, dp[i-1][j]+1, dp[i][j-1]+1)
	 * <p>
	 * O(mn) time + O(mn) space
	 * 可以继续优化为O(n)
	 */
	public int minDistance(String word1, String word2) {
		if (word1.length() == 0 || word2.length() == 0)
			return word1.length() == 0 ? word2.length() : word1.length();

		int[][] dp = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i <= word1.length(); ++i) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= word2.length(); ++j) {
			dp[0][j] = j;
		}
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

	/**
	 * 优化为O(n) space
	 */
	public int minDistance2(String word1, String word2) {
		if (word1.length() == 0 || word2.length() == 0)
			return word1.length() == 0 ? word2.length() : word1.length();

		int[] dp = new int[word1.length() + 1];
		for (int i = 0; i <= word1.length(); ++i) {
			dp[i] = i;
		}
		for (int j = 1; j <= word2.length(); ++j) {
			int pre = dp[0];
			dp[0] = j;
			for (int i = 1; i <= word1.length(); ++i) {
				int temp = dp[i];           //更改i位置之前先保存一下该位置的值 计算下一个位置的时候需要这个值
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
