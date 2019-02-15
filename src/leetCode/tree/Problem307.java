package leetCode.tree;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * <p>
 * Example:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * <p>
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 * <p>
 * create by stephen on 2019/2/15
 */
public class Problem307 {


    // todo sparse table

    /**
     * 实现一个Segment Tree即可
     */
    class SumSegmentTree {
        int[] tree;
        int n;

        public SumSegmentTree(int[] nums) {
            this.n = nums.length;
            this.tree = new int[nums.length * 2];
            System.arraycopy(nums, 0, tree, n, nums.length);
            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[2 * i] + tree[2 * i + 1];
            }
        }

        public void update(int i, int val) {
            i += n;
            tree[i] = val;
            while (i > 1) {
                i /= 2;
                tree[i] = tree[2 * i] + tree[2 * i + 1];
            }
        }

        public int sumRange(int i, int j) {
            i += n;
            j += n;
            int sum = 0;
            while (i <= j) {
                if ((i % 2) == 1) {
                    sum += tree[i];
                    i++;
                }
                if ((j % 2) == 0) {
                    sum += tree[j];
                    j--;
                }
                i /= 2;
                j /= 2;
            }
            return sum;
        }
    }
}
