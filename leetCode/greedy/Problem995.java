package leetCode.greedy;

/**
 * In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray
 * of length K and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
 * Return the minimum number of K-bit flips required so that there is no 0 in the array.
 * If it is not possible, return -1.
 */
public class Problem995 {
    /**
     * 贪心算法
     * 对于最左边的一位
     * 1. 如果最左一位为0，必须flip以最左开头的这个子数组
     * 2. 如果最左一位为1，不能flip最左开头的这个子数组，一旦flip了，必须还要flip回来，
     * 两次动作对原数组没有任何影响，但这样就不是次数最少的解法
     */
    public int minKBitFlips(int[] A, int K) {
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                if (i + K > A.length) return -1;
                for (int j = i; j < i + K; j++) {
                    A[j] ^= 1;
                }
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 贪心算法优化 一次遍历
     */
    public int minKBitFlips1(int[] A, int K) {
        // Tell when to close an interval
        boolean[] needClose = new boolean[A.length];
        int ans = 0, nIntervals = 0;
        for (int i = 0; i < A.length; i++) {
            // Close this interval if needed
            if (needClose[i]) nIntervals--;
            // When meet following two situations, we need flipping here
            // if A[i] is 0 and number of intervals is even
            // --> means the flippings are totally cancelled. We need another flip
            // if A[i] is 1 and number of intervals is odd
            // --> means we have 1 before but being flipped to 0. Need flip again.
            if ((A[i] == 0 && nIntervals % 2 == 0) ||
                    (A[i] == 1 && nIntervals % 2 == 1)) {
                // Need flip again. Update answer count
                ++ans;
                // Generate an interval
                ++nIntervals;
                if (i > A.length - K) {
                    // A[n-K] is the final possible flipping position
                    // i > n-K means we need to flip the subarray is less than K elements
                    // impossible!
                    return -1;
                }
                // Update needClose, so the current interval will be closed at A[i+K]
                if (i + K < A.length) needClose[i + K] = true;
            }
        }
        return ans;
    }
}
