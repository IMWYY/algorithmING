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
        System.out.println(divide(-2147483648, 2));
    }

    /**
     * 除法所用的位操作 参见 https://leetcode.com/problems/divide-two-integers/discuss/13407/Detailed-Explained-8ms-C++-solution
     * 这里只关心一种特殊情况 即 MIN_VALUE/-1，因为如果将这种情况while循环计算，结果会加到大于MAX_VALUE
     * 其他情况直接将dividend转换为long进行计算 防止出现被除数为溢出
     */
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1) {
            return dividend;
        } else if (divisor == -1) {
            return -dividend;
        }
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        // 需要注意这里应该用long 处理int_min的溢出情况
        long dvd = Math.abs((long) dividend), dvs = Math.abs((long) divisor);

        int res = 0;
        while (dvd >= dvs) {
            long temp = dvs, multiple = 1;
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            if (res < Integer.MAX_VALUE) {
                res += multiple;
            }
        }
        return sign == 1 ? res : -res;
    }

    /**
     * 不用long类型
     * 因为Math.abs(-2147483648)=-2147483648 会overflow
     * 所以先把dividend和divisor等于Integer.MIN_VALUE的情况处理掉
     */
    public int divide1(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) return Integer.MAX_VALUE;
            if (divisor == Integer.MIN_VALUE) return 1;
            if (divisor < 0) {
                return 1 + divide1(dividend + Math.abs(divisor), divisor);
            } else {
                return -1 + divide1(dividend + Math.abs(divisor), divisor);
            }
        }
        if (divisor == Integer.MIN_VALUE) return 0;

        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1, quotient = 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor); //Math.abs(-2147483648)=-2147483648
        while (dividend >= divisor) {
            int tmp = divisor, count = 1;
            while (dividend - tmp >= tmp) { //(dividend >= tmp<<1 or >= 2*tmp) may cause overflows
                tmp <<= 1;
                count <<= 1;
            }
            dividend -= tmp;
            quotient += count;
        }
        return sign * quotient;
    }


}
