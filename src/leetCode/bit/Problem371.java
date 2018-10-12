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
     */
    public int getSum(int a, int b) {
        while (b != 0) {
            int same = a & b;
            a = a ^ b;
            b = same << 1;
        }
        return a;
    }
}
