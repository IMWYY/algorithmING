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
		System.out.println(divide(-2147483648,2));
	}

	// 利用位操作 很巧妙
	// 参见 https://leetcode.com/problems/divide-two-integers/discuss/13407/Detailed-Explained-8ms-C++-solution
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
		long dvd = Math.abs((long) dividend);
		System.out.println(dvd);
		long dvs = Math.abs((long) divisor);

		int res = 0;
		while (dvd >= dvs) {
			long temp = dvs, multiple = 1;
			while (dvd >= (temp << 1)) {
				temp <<= 1;
				multiple <<= 1;
			}
			dvd -= temp;
			res += multiple;
		}
		return sign == 1 ? res : -res;
	}

	// 考虑了边界条件 但是时间复杂度太高
	public int divide1(int dividend, int divisor) {
		if (divisor == 1)
			return dividend;
		if (dividend == divisor)
			return 1;
		if (divisor == Integer.MIN_VALUE) {
			return 0;
		}

		boolean isPos = true;
		if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
			isPos = false;
		}
		int res = 0;
		while ((dividend > 0 && divisor > 0 && dividend - divisor >= 0) ||
				(dividend > 0 && divisor < 0 && dividend + divisor >= 0) ||
				(dividend < 0 && divisor > 0 && dividend + divisor <= 0) ||
				(dividend < 0 && divisor < 0 && dividend - divisor <= 0)) {
			if (isPos) {
				if (res == Integer.MAX_VALUE) {
					return Integer.MAX_VALUE;
				}
				res++;
			} else {
				if (res == Integer.MIN_VALUE) {
					return Integer.MAX_VALUE;
				}
				res--;
			}
			if ((dividend > 0 && divisor > 0 || (dividend < 0 && divisor < 0))) {
				dividend -= divisor;
			}  else {
				dividend += divisor;
			}
		}
		return res;
	}
}
