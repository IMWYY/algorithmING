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


    /**
     * binary index tree
     * BIT[i] 覆盖的范围是 lowbit(i) = i&-i
     * 如i=13 lowbit(13) = lowbit(0b00001101) = lowbit(00000001) = 1，从13起前1个数字的和
     * 如i=8 lowbit(8) = lowbit(0b00001000) = 8 从8起前8个数字的和
     */
    class BinaryIndexTree {
        int[] nums;
        int[] BIT;
        int n;

        public BinaryIndexTree(int[] nums) {
            this.nums = nums;
            n = nums.length;
            BIT = new int[n + 1];
            for (int i = 0; i < n; i++)
                init(i, nums[i]);
        }

        void init(int i, int val) {
            i++;
            while (i <= n) {
                BIT[i] += val;
                i += (i & -i);
            }
        }

        public void update(int i, int val) {
            int diff = val - nums[i];
            nums[i] = val;
            init(i, diff);
        }

        int getSum(int i) {
            int sum = 0;
            i++;
            while (i > 0) {
                sum += BIT[i];
                i -= (i & -i);
            }
            return sum;
        }

        public int sumRange(int i, int j) {
            return getSum(j) - getSum(i - 1);
        }
    }

    /**
     * 实现一个Segment Tree即可
     * update和sumRange都是O(nlogn)
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

    /**
     * 类似sparse table 记录一个长度为sqrt(n)的数组
     * 每个值代表原数组一个block的sum
     */
    class SqrtDecomposition {
        private int[] b;
        private int len;
        private int[] nums;

        public SqrtDecomposition(int[] nums) {
            this.nums = nums;
            double l = Math.sqrt(nums.length);
            len = (int) Math.ceil(nums.length / l);
            b = new int[len];
            for (int i = 0; i < nums.length; i++)
                b[i / len] += nums[i];
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            int startBlock = i / len;
            int endBlock = j / len;
            if (startBlock == endBlock) {
                for (int k = i; k <= j; k++)
                    sum += nums[k];
            } else {
                for (int k = i; k <= (startBlock + 1) * len - 1; k++)
                    sum += nums[k];
                for (int k = startBlock + 1; k <= endBlock - 1; k++)
                    sum += b[k];
                for (int k = endBlock * len; k <= j; k++)
                    sum += nums[k];
            }
            return sum;
        }

        public void update(int i, int val) {
            int b_l = i / len;
            b[b_l] = b[b_l] - nums[i] + val;
            nums[i] = val;
        }
    }
}
