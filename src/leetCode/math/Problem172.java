package leetCode.math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Example 1:
 * <p>
 * Input: 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 * <p>
 * Input: 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * create by stephen on 2018/9/14
 */
public class Problem172 {

    /**
     * 10 = 2 *5 只要计算5的个数。5的个数来自与
     * 5, 5x5, 5x5x5, 5x5x5x5....
     * 所有每增加一个5的幂次 都要再加一个5的个数
     * ！！注意这里的i必须声名为long 否则int溢出 会多计算
     */
    public int trailingZeroes(int n) {
        // 或者利用递归
        // return n ==0 ? 0 : n/5 + trailingZeroes(n/5);
        int result = 0;
        for(long i=5; n/i>0; i*=5){
            result += (n/i);
        }
        return result;
    }
}
