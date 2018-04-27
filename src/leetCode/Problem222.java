package leetCode;

import classic.TreeNode;

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

    public int countNodes(TreeNode root) {
        int h = height(root);
        if (h <= 1) {
            return h;
        }
        return height(root.right) == h - 1 ? (1 << h - 1) + countNodes(root.right) :
                (1 << h - 2) + countNodes(root.left);
    }

    public int height(TreeNode node) {
        int result = 0;
        while (node != null) {
            result++;
            node = node.left;
        }
        return result;
    }
}
