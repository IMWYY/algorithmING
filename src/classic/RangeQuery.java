package classic;

/**
 * 区间最值查询：对于长度为n的数列A，回答若干次询问RMQ(i,j)，返回数列A中下标在区间[i,j]中的最小/大值。
 * <p>
 * 解法1：
 * ST（Sparse Table）O(nlogn)预处理，O(1)回答每个查询，占用space较小
 * 解法2：
 * 线段树（Segment Tree）O(n)预处理，O(1)回答每个查询，占用spcae较大
 * 线段树不仅可以回答最大最小的查询，还可以计算range sum等
 * <p>
 * create by stephen on 2018/4/27
 */
public class RangeQuery {

    /**
     * 利用dp算法进行预处理
     * table[i][j]表示以i开头，长度为 1<<j 的区间的最大值
     * 故dp方程为：
     * table[i][j] = Max{table[i][j-1], table[i+(1<<(j-1))][j-1]}
     * 即将1<<j的长度分成前后两部分长度为1<<(j-1)的区间
     */
    public int sparseTable(int[] nums, int s, int e) {
        int[][] table = new int[nums.length][(int) Math.log(nums.length) + 1];
        for (int i = 0; i < table.length; ++i) {
            table[i][0] = nums[i];
        }
        for (int j = 1; j < table[0].length; ++j) {
            for (int i = 0; i < table.length; ++i) {
                table[i][j] = Math.max(table[i][j - 1], table[i + (1 << j - 1)][j - 1]);
            }
        }
        int k = (int) Math.log(e - s + 1);  //对应的2的幂次
        return Math.max(table[s][k], table[e - (1 << k) + 1][k]);
    }


}
