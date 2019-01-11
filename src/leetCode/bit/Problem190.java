package leetCode.bit;

/**
 * <p>
 * Reverse bits of a given 32 bits unsigned integer.
 * <p>
 * Example:
 * <p>
 * Input: 43261596
 * Output: 964176192
 * Explanation: 43261596 represented in binary as 00000010100101000001111010011100,
 * return 964176192 represented in binary as      00111001011110000010100101000000.
 * create by stephen on 2018/10/13
 */
public class Problem190 {

    public static void main(String[] args) {
        System.out.println((Integer.MAX_VALUE) >> 1);
    }

    /**
     * you need treat n as an unsigned value
     * 这里要注意可能传入比Integer.MAX_VALUE更大的数字 需要把它看作无符号整数
     * 所以if判断的条件是 (n & mask) != 0 而不是 (n & mask) > 0
     */
    public int reverseBits(int n) {
        int mask = 1, res = 0;
        for (int i = 0; i < 32; ++i) {
            res <<= 1;
            if ((n & mask) != 0) res |= 1;
            mask <<= 1;
        }
        return res;
    }

    public int reverseBits1(int n) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            res <<= 1;
            if ((n & 1) == 1) {
                res |= 1;
            }
            n >>>= 1;  // 注意这里是无符号右移
        }
        return res;
    }

    /**
     * for 8 bit binary number abcdefgh, the process is as follow:
     * abcdefgh -> efghabcd -> ghefcdab -> hgfedcba
     * 要求是无符号整数 在java里需要用>>>
     */
    public int reverseBits2(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }
}
