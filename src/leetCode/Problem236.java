package leetCode;


/**
 * 最近公共父节点问题
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes v and w as the lowest
 * node in T that has both v and w as descendants
 * (where we allow a node to be a descendant of itself).”
 * create by stephen on 2018/4/21
 */
public class Problem236 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归解决
     * 如果子树中包含了p和q，那么直接返回公共节点；
     * 如果子树中仅包含一个节点，返回该节点；
     * 如果子树中不包含任何节点 返回null
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;

        if (left != null) return left;
        if (right != null) return right;
        return null;
    }
}
