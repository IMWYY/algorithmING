package leetCode.hash;

import java.util.HashSet;
import java.util.List;

/**
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the process until the number
 * equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 */
public class Problem202 {

    public boolean isHappy(int n) {
        HashSet<Integer> map = new HashSet<>();

        while (n != 1) {
            int t = 0;
            while (n > 0) {
                t += Math.pow(n%10, 2);
                n /= 10;
            }
            if (map.contains(t)) return false;
            map.add(t);
            n = t;
        }

        return true;
    }
}
