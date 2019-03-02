package leetCode.tree;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to
 * any node in the tree along the parent-child connections. The path must contain at least one node
 * and does not need to go through the root.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * Output: 6
 * create by stephen on 2018/6/14
 */
public class Problem124 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int ans = Integer.MIN_VALUE;

    /**
     * 递归
     * 对于每个节点 最大路径可能是
     * 1.左子树+当前节点
     * 2.当前节点
     * 3.右子树+当前节点
     * 必须带当前节点 否则递归会不成立
     */
    public int maxPathSum(TreeNode root) {
        findPath(root);
        return ans;
    }

    private int findPath(TreeNode root) {
        if (root == null) return 0;
        int res = root.val;
        int l = findPath(root.left);
        int r = findPath(root.right);
        if (l > 0) res += l;
        if (r > 0) res += r;
        ans = Math.max(ans, res);
        // 返回的时候只能返回带一个子树的
        return Math.max(Math.max(root.val, root.val + l), root.val + r);
    }

}
