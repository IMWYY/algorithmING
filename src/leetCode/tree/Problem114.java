package leetCode.tree;


/**
 * Given a binary tree, flatten it to a linked list in-place.
 * create by stephen on 2018/6/12
 */
public class Problem114 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 对左右子树分别flatten
     * 找到左子树最右边节点 接到右子树
     **/
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

    /**
     * 递归法
     * 对于每个子节点 将其左子树搬到右子树来
     */
    public void flatten1(TreeNode root) {
        TreeNode now = root;
        while (now != null) {
            if (now.left != null) {
                //Find current node's prenode that links to current node's right subtree
                TreeNode pre = now.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = now.right;
                //Use current node's left subtree to replace its right subtree(original right
                //subtree is already linked by current node's prenode
                now.right = now.left;
                now.left = null;
            }

            now = now.right;
        }
    }


}
