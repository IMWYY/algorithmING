package leetCode;

import java.util.HashMap;

/**
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * <p>
 * Example:
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * Return 3. The paths that sum to 8 are:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 * <p>
 * create by stephen on 2018/6/11
 */
public class Problem437 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /******************
     * DFS
     *******************/
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum)
                + pathSum(root.left, sum)
                + pathSum(root.right, sum);

    }

    // 以node为起点的路径数量
    private int pathSumFrom(TreeNode node, int target) {
        if (node == null) return 0;
        return (node.val == target ? 1 : 0)
                + pathSumFrom(node.left, target - node.val)
                + pathSumFrom(node.right, target - node.val);
    }

    /************************
     * 利用 map+前缀和
     * key-之前的和 value-出现的次数 即到当前点为止 可以得到该value的路径数量
     * 以 1,2,-1,-1,2为例，presum为 1, 3, 2, 1, 3 路径为{2}, {1,2,-1}, {2,-1,-1,2}, {2}
     ***********************/
    private int count;

    public int pathSum1(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);       // 保证节点值等于sum的情况
        findPath(root, 0, sum, preSum);
        return count;
    }

    private void findPath(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) return;

        currSum += root.val;

        if (preSum.containsKey(currSum - target)) {
            count += preSum.get(currSum - target);
        }

        if (!preSum.containsKey(currSum)) {
            preSum.put(currSum, 1);
        } else {
            preSum.put(currSum, preSum.get(currSum) + 1);
        }

        findPath(root.left, currSum, target, preSum);
        findPath(root.right, currSum, target, preSum);

        preSum.put(currSum, preSum.get(currSum) - 1);       // 容易遗漏
    }
}
