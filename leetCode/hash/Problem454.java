package leetCode.hash;

import java.util.HashMap;

/**
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that
 * A[i] + B[j] + C[k] + D[l] is zero.
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
 * All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 */
public class Problem454 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int aA : A) {
            for (int aB : B) {
                int t = aA + aB;
                map.put(t, map.getOrDefault(t, 0) + 1);
            }
        }

        for (int c : C) {
            for (int d : D) {
                int t = c + d;
                res += map.getOrDefault(-t, 0);
            }
        }
        return res;
    }

}
