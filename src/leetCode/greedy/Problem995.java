package leetCode.greedy;

/**
 * In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray
 * of length K and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
 * Return the minimum number of K-bit flips required so that there is no 0 in the array.
 * If it is not possible, return -1.
 * <p>
 * Example 1:
 * Input: A = [0,1,0], K = 1
 * Output: 2
 * Explanation: Flip A[0], then flip A[2].
 * <p>
 * Example 2:
 * Input: A = [1,1,0], K = 2
 * Output: -1
 * Explanation: No matter how we flip subarrays of size 2, we can't make the array become [1,1,1].
 * <p>
 * Example 3:
 * Input: A = [0,0,0,1,0,1,1,0], K = 3
 * Output: 3
 * Explanation:
 * Flip A[0],A[1],A[2]: A becomes [1,1,1,1,0,1,1,0]
 * Flip A[4],A[5],A[6]: A becomes [1,1,1,1,1,0,0,0]
 * Flip A[5],A[6],A[7]: A becomes [1,1,1,1,1,1,1,1]
 * <p>
 * create by stephen on 2019/2/17
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
        int N = A.length;
        int[] hint = new int[N];
        int ans = 0, flip = 0;

        // When we flip a subarray like A[i], A[i+1], ..., A[i+K-1]
        // we can instead flip our current writing state, and put a hint at
        // position i+K to flip back our writing state.
        for (int i = 0; i < N; ++i) {
            flip ^= hint[i];
            if (A[i] == flip) {  // If we must flip the subarray starting here...
                ans++;  // We're flipping the subarray from A[i] to A[i+K-1]
                if (i + K > N) return -1;  //If we can't flip the entire subarray, its impossible
                flip ^= 1;
                if (i + K < N) hint[i + K] ^= 1;
            }
        }

        return ans;
    }
}
