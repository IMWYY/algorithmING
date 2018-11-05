package leetCode.tree;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * <p>
 * Example:
 * <p>
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * create by stephen on 2018/5/28
 */
public class Problem096 {

    /**
     * 对于每个BST选取不同的节点作为root，那么BST也不同
     * dp[i]记录使用i个数字得到的不同BST个数
     * O(n^2) time + O(n) space
     */
    public int numTrees(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {      // 选取root节点 左右两子数也是BST 两者相乘
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * Actually, as it turns out, the sequence of G(n) function results is known as Catalan number Cn
     * And one of the more convenient forms for calculation is defined as follows:
     */
    public int numTrees1(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }
}
