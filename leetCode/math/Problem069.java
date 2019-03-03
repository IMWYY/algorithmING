package leetCode.math;

/**
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * Since the return type is an integer, the decimal digits are truncated and
 * only the integer part of the result is returned.
 * <p>
 * Example 1:
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 * create by stephen on 2018/9/15
 */
public class Problem069 {

    /**
     * 二分查找 O(logn)时间
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x < 4) return 1;
        long left = 0, right = x / 2;
        while (left < right) {
            long mid = (left + right) / 2;
            if (mid * mid == x || (mid * mid < x && (mid + 1) * (mid + 1) > x)) return (int) mid;
            if (mid * mid < x) left = mid + 1;
            if (mid * mid > x) right = mid - 1;
        }
        return  (int)left;
    }

    /**
     * 遍历一边 O(n)时间
     */
    public int mySqrt1(int x) {
        if (x == 0) return 0;
        if (x < 4) return 1;
        for (long i = 2; i < x / 2; ++i) {
            if (i * i == x || (i * i < x && (i + 1) * (i + 1) > x)) return (int) i;
        }
        return x / 2;
    }
}
