package classic;

/**
 * 区间最值查询：对于长度为n的数列A，回答若干次询问RMQ(i,j)，返回数列A中下标在区间[i,j]中的最小/大值。
 * <p>
 * ST（Sparse Table）算法可以在O(nlogn)时间内进行预处理，然后在O(1)时间内回答每个查询。
 * <p>
 * create by stephen on 2018/4/27
 */
public class RMQ {

    /**
     * 利用dp算法进行预处理
     * table[i][j]表示以i开头，长度为 1<<j 为长度的区间的最大值
     */
    public int[][] sparseTable(int[] nums) {
        int[][] table = new int[nums.length][(int) Math.log(nums.length) + 1];
        for (int i = 0; i < table.length; ++i) {
            table[i][0] = nums[i];
        }
        for (int j = 1; j < table[0].length; ++j) {
            for (int i = 0; i < table.length; ++i) {
                table[i][j] = Math.max(table[i][j - 1], table[i + (1 << j - 1)][j - 1]);
            }
        }
        return table;
    }

    /**
     * 将给定区间从前往后取 1<<k 长度， 从后往前取 1<<k 长度
     * 两个区间的长度的最大值即是结果
     */
    public int rmq(int[] nums, int s, int e) {
        int[][] dp = sparseTable(nums);
        int k = (int) Math.log(e - s + 1);
        return Math.max(dp[s][k], dp[e - (1 << k) + 1][k]);
    }
}
