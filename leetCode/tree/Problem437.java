package leetCode.tree;

import java.util.HashMap;

/**
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 */
public class Problem437 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x;}
    }

    /******************
     * Simple recursive solution, each node can be the starting node or not
     * Space: O(n) due to recursion.
     * Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
     *******************/
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum)
                + pathSum(root.left, sum)
                + pathSum(root.right, sum);
    }
    // the number of paths starting from @node, with a sum of @target
    private int pathSumFrom(TreeNode node, int target) {
        if (node == null) return 0;
        return (node.val == target ? 1 : 0)
                + pathSumFrom(node.left, target - node.val)
                + pathSumFrom(node.right, target - node.val);
    }

    /************************
     * prefix sum solution
     * for each path starting from root to the leaf node, we can
     * regard it as an array, then keep a prefix sum.
     ***********************/
    public int pathSum1(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }

    private int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) { return 0;}
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1); // restore the previous value
        return res;
    }
}
