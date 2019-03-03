package leetCode.tree;

/**
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * In a complete binary tree every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * https://leetcode.com/problems/count-complete-tree-nodes/description/
 * <p>
 * create by stephen on 2018/4/18
 */
public class Problem222 {
    private class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * 利用完全二叉树的性质 用树高来计算节点数
     * 如果右子树与跟节点树高相差1，
     * 那么左子树是满二叉树，继续计算右子树
     * 否则右子树是满二叉树，继续计算左子树
     */
    public int countNodes(TreeNode root) {
        int h = height(root);
        if (h <= 1) return h;
        return height(root.right) == h - 1 ? (1 << h - 1) + countNodes(root.right) :
                (1 << h - 2) + countNodes(root.left);
    }

    private int height(TreeNode node) {
        int result = 0;
        while (node != null) {
            result++;
            node = node.left;
        }
        return result;
    }
}
