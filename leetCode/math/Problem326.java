package leetCode.math;

/**
 * Given an integer, write a function to determine if it is a power of three.
 */
public class Problem326 {

    // solution 1: without a loop or recursion
    // we can use this to detect power of a prime number
    public boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int
        return ( n>0 &&  1162261467%n==0);
    }

    // solution 2: the power of three has a relationship with its length
    public boolean isPowerOfThree1(int n) {
        if (n>=Integer.MAX_VALUE) return false;
        if (n==1) return true;
        String s = String.valueOf(n);
        int len = s.length();
        if (n==(int)Math.pow(3, 2*len-1) || n==(int)Math.pow(3, 2*len)) return true;
        else return false;
    }

    // solution3: with a loop
    public boolean isPowerOfThree2(int n) {
        if (n == 1 || n == 3) return true;
        if (n < 3) return false;
        double temp = n;
        while (n != 1) {
            temp = n / 3.0;
            if (temp != n/3) return false;
            n /= 3;
        }
        return true;
    }
}
