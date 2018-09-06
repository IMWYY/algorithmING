package leetCode.math;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Example:
 * <p>
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * create by stephen on 2018/9/6
 */
public class Problem204 {

    /**
     * prime数的倍数不是prime 每次找到一个prime 将其所有的倍数标为非prime
     */
    public int countPrimes(int n) {
        int count = 0;
        boolean[] notPrime = new boolean[n];
        for (int i = 2; i < n; ++i) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; ++j) {
                    notPrime[i * j] = true;
                }
            }
        }

        return count;
    }
}
