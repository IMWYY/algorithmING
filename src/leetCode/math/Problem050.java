package leetCode.math;

/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * <p>
 * Example 1:
 * Input: 2.00000, 10
 * Output: 1024.00000
 * <p>
 * Example 2:
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * create by stephen on 2018/9/20
 */
public class Problem050 {

    /**
     * 直接计算pow会stackoverflow
     * 所以这里使用类似于二分的方法
     * 需要处理n=INT_MIN的情况 因为n=-INT_MIN会溢出
     */
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == Integer.MIN_VALUE) {
            return 1 / x * myPow(x, n + 1);
        } else if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
