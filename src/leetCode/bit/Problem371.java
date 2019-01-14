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
     * a&b找到两个数相同的部分 所以要左移一位（x2）
     * a^b找到两个数不同的部分
     * 直到没有相同部分（b=0） 直接返回a
     * 一个例子解释：https://leetcode.com/problems/sum-of-two-integers/discuss/132479/Simple-explanation-on-how-to-arrive-at-the-solution
     *
     * 当a和b的所有对应位置的1和0不重复时 a^b可以得到a+b, 但是当有两个1时，就会有进位问题。
     */
    public int getSum(int a, int b) {
        if (a == 0) return b;
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
}
