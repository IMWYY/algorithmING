package leetCode;


/**
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example, given the following tree:
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * create by stephen on 2018/6/12
 */
public class Problem114 {


    /**********************************
     * 找到左子树最右边节点 接到右子树
     **********************************/
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = temp;
    }

    private TreeNode flattenNode(TreeNode root) {
        if (root == null) return null;
        TreeNode l = flattenNode(root.left);
        TreeNode res = flattenNode(root.right);
        if (res == null) {
            res = root;
        }
        if (l != null) {
            l.right = root.right;
            root.right = root.left;
        }
        root.left = null;
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
