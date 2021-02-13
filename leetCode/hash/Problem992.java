package leetCode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of
 * A good if the number of different integers in that subarray is exactly K.
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 * Return the number of good subarrays of A.
 */
public class Problem992 {

    public static void main(String[] args) {
        Problem992 p = new Problem992();
        System.out.println(p.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
        System.out.println(p.subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));
        System.out.println(p.subarraysWithKDistinct(new int[]{1, 1, 1, 2}, 1));
        System.out.println(p.subarraysWithKDistinct(new int[]{1, 1, 1, 2}, 2));
    }

    /**
     * 转换为atMostK 因为最多K个不同元素是比较方便计算的
     * O(n) time + O(n) space
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    private int atMostK(int[] A, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < A.length; ++j) {
            if (count.getOrDefault(A[j], 0) == 0) K--;
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0) K++;
                i++;
            }
            res += j - i + 1;   //这里只是[i,j]之间以i开头的子数组
        }
        return res;
    }

    /**
     * 利用two pointer和hashmap 每次当map size>k时，start++
     * 时间复杂度略高，在没有加这一行代码时time exceed limit
     * if (map.size() < K && end == A.length) {
     * break;
     * }
     */
    public int subarraysWithKDistinct1(int[] A, int K) {
        if (A.length == 1) return 1;
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0, end = 0, res = 0;

        while (start <= A.length - K) {
            while (map.size() < K && end < A.length) {
                map.put(A[end], map.getOrDefault(A[end], 0) + 1);
                end++;
            }
            if (map.size() < K && end == A.length) {
                break;
            }
            while (map.size() == K) {
                res++;
                if (end == A.length) {
                    break;
                } else {
                    map.put(A[end], map.getOrDefault(A[end], 0) + 1);
                    end++;
                }
            }
            map.clear();
            start++;
            end = start;
        }
        return res;
    }
}
