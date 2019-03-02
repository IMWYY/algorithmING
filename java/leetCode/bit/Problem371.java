package leetCode.bit;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 2
 * Output: 3
 * create by stephen on 2018/10/12
 */
public class Problem371 {
	/**
	 * 一个例子解释：https://leetcode.com/problems/sum-of-two-integers/discuss/132479/Simple-explanation-on-how-to-arrive-at-the-solution
	 * <p>
	 * 当a+b没有进位的时候，a^b可以得到a+b的结果
	 * 当有进入的时候，a&b可以拿到所有的进位，进位需要左移（进位上1）
	 * 继续循环 知道进位为0 则结果a+b = a^b
	 */
	public int getSum(int a, int b) {
		if (a == 0)
			return b;
		while (b != 0) {
			int carry = a & b;
			a = a ^ b;
			b = carry << 1;
		}
		return a;
	}
}
