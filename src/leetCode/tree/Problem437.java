package leetCode.tree;

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

    /******************
     * DFS
     * Space: O(n) due to recursion.
     * Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
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
        return (node.val == target ? 1 : 0)     // 如果val == target 不能终止递归 接下来的pathSum可能是0 不能漏考虑
                + pathSumFrom(node.left, target - node.val)
                + pathSumFrom(node.right, target - node.val);
    }

    /************************
     * 利用 map+前缀和 不太好理解
     * key-前缀和 value-到当前点为止 可以得到该前缀和的路径数量
     * 以 1,2,-1,-1,2为例，target=2
     * presum为 1, 3, 2, 1, 3 路径为{2}, {1,2,-1}, {2,-1,-1,2}, {2}
     ***********************/
    public int pathSum1(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0,1); // 插入一个(0,1)是为了能够cover preSum=node.val的情况 如例子中presum=2 target=2的情况
        return helper(root, 0, sum, preSum);
    }

    private int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) { return 0;}

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1); // 递归后还原presum的值
        return res;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
