package leetCode.math;

/**
 * Given an integer, write a function to determine if it is a power of three.
 * create by stephen on 2018/9/13
 */
public class Problem326 {

    public static void main(String[] args) {
        int a = 27;
        double b = 27 / 3.0;
        b = b / 3.0;
        System.out.println(b == 3);
    }

    /**
     * 没有用循环
     */
    public boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int
        return ( n>0 &&  1162261467%n==0);
    }

    /**
     * 利用数学规律 数字的长度和3的幂次的关系
     */
    public boolean isPowerOfThree1(int n) {
        if (n>=Integer.MAX_VALUE) return false;
        if (n==1) return true;
        String s = String.valueOf(n);
        int len = s.length();
        if (n==(int)Math.pow(3, 2*len-1) || n==(int)Math.pow(3, 2*len)) return true;
        else return false;
    }

    /**
     * 有循环
     */
    public boolean isPowerOfThree2(int n) {
        if (n == 1 || n == 3) return true;
        if (n < 3) return false;
        double temp = n;
        while (n != 1) {
            temp = n / 3.0;
            if (temp != n/3) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
