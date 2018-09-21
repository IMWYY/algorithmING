package leetCode.math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication,
 * division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * <p>
 * Note:
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed
 * integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function
 * returns 231 − 1 when the division result overflows.
 * create by stephen on 2018/9/21
 */
public class Problem029 {

    public static void main(String[] args) {
        System.out.println(divide(Integer.MIN_VALUE, -3));
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 1) return dividend;
        if (dividend == divisor) return 1;
        if (divisor == Integer.MIN_VALUE) {
            return 0;
        }

        boolean isPos = true;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            isPos = false;
        }
        int res = 0;
        boolean canGoOn = true;
        while ((dividend > 0 && dividend - divisor > 0)) {
            if (isPos) {
                if (res == Integer.MAX_VALUE) {

                }
                res++;
            } else {
                if (res == Integer.MIN_VALUE) {
                    return Integer.MAX_VALUE;
                }
                res--;
            }
            dividend -= divisor;
        }
        return res;
    }
}
