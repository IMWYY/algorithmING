package leetCode.bit;

/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 * <p>
 * Example 1:
 * <p>
 * Input: 11
 * Output: 3
 * Explanation: Integer 11 has binary representation 00000000000000000000000000001011
 * create by stephen on 2018/10/12
 */
public class Problem191 {
    public static void main(String[] args) {
        int a = 2, b= 2;
        System.out.println(a ^ b);
    }

    /**
     * 位操作的trick 每次 n &= (n - 1)都会将最右边的一个1变成0
     * 注意while循环的条件是 n != 0而不是 n >0 如输入数字为Integer.MAX_VALUE + 1
     */
    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    /**
     * 利用mask一位一位的检查
     */
    public int hammingWeight1(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }

}
