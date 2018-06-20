package leetCode;

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

    private int ans = Integer.MIN_VALUE;

    /**
     * 递归即可 最大路径就出现在某节点左右子树路径和
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

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
