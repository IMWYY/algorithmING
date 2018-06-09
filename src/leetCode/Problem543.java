package leetCode;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * create by stephen on 2018/6/6
 */
public class Problem543 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /*******************优化解法*********************/
    int ans = 0;
    /**
     * DFS算法 只要求出每个节点的深度即可
     * 递归求解 减少了很多重复计算
     */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return ans;
    }

    private int maxDepth(TreeNode node) {
        if (node == null) return 0;
        int l = maxDepth(node.left);
        int r = maxDepth(node.right);
        ans = Math.max(ans, l + r);
        return Math.max(l, r) + 1;
    }


    /*******************未优化解法*********************
     * 递归算法 非递归求树高
     */
    public int diameterOfBinaryTree1(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(Math.max(diameterOfBinaryTree(root.left),
                diameterOfBinaryTree(root.right))
                , Math.abs(leftHeight + rightHeight));

    }
    private int height(TreeNode node) {
        if (node == null) return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);
        int h = 0, len;
        while (!queue.isEmpty()) {
            len = queue.size();
            h ++;
            while (len-- > 0) {
                TreeNode temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
        }
        return h;
    }
}
