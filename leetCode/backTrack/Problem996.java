package leetCode.backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements,
 * their sum is a perfect square.
 * Return the number of permutations of A that are squareful.
 * Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].
 */
public class Problem996 {

    public static void main(String[] args) {
        Problem996 p = new Problem996();
        System.out.println(p.numSquarefulPerms(new int[]{1, 17, 8}));
        System.out.println(p.numSquarefulPerms(new int[]{2, 2, 2}));
        System.out.println(p.numSquarefulPerms(new int[]{0, 0, 0, 1, 1, 1}));
    }

    private int res;

    public int numSquarefulPerms(int[] A) {
        res = 0;
        Arrays.sort(A);
        backTrack(A, new ArrayList<>(), new boolean[A.length]);
        return res;
    }

    private void backTrack(int[] A, List<Integer> list, boolean[] used) {
        if (list.size() == A.length) {
            res++;
        } else {
            for (int i = 0; i < A.length; ++i) {
                if (used[i] || i > 0 && A[i] == A[i - 1] && !used[i - 1]) continue;
                if (list.size() > 0) {
                    int sq = (int) Math.sqrt(list.get(list.size() - 1) + A[i]);
                    if (sq * sq != list.get(list.size() - 1) + A[i]) continue;
                }
                used[i] = true;
                list.add(A[i]);
                backTrack(A, list, used);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }
}
