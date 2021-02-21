package leetCode.tree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 * Note:
 * The number of nodes in the tree will be between 2 and 100.
 * Each node has a unique integer value from 1 to 100.
 */
public class Problem993 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * level-order traversal
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null || root.val == x || root.val == y || x == y) return false;
        queue.add(root);
        boolean xExist, yExist;
        int count, size;
        while (!queue.isEmpty()) {
            size = queue.size();
            xExist = yExist = false;
            for (int i = 0; i < size; ++i) {
                TreeNode n = queue.poll();
                count = 0;
                if (n.left != null) {
                    if (n.left.val == y) {
                        yExist = true;
                        count++;
                    } else if (n.left.val == x) {
                        xExist = true;
                        count++;
                    }
                    queue.add(n.left);
                }
                if (n.right != null) {
                    if (n.right.val == y) {
                        yExist = true;
                        count++;
                    } else if (n.right.val == x) {
                        xExist = true;
                        count++;
                    }
                    queue.add(n.right);
                }
                if (count > 1) return false;
            }
            if (xExist && yExist) return true;
            else if (xExist || yExist) return false;
        }
        return true;
    }

    /**
     * 遍历一遍树 获得x和y节点对应depth和parent
     * 按条件比较两者
     * O(n) time + O(n) space
     */
    public boolean isCousins1(TreeNode root, int x, int y) {
        Map<Integer, Integer> depth = new HashMap<>();
        Map<Integer, TreeNode> parent = new HashMap<>();
        dfs(root, null, depth, parent);
        return (depth.get(x).equals(depth.get(y)) && parent.get(x) != parent.get(y));
    }

    public void dfs(TreeNode node, TreeNode par, Map<Integer, Integer> depth, Map<Integer, TreeNode> parent) {
        if (node != null) {
            depth.put(node.val, par != null ? 1 + depth.get(par.val) : 0);
            parent.put(node.val, par);
            dfs(node.left, node, depth, parent);
            dfs(node.right, node, depth, parent);
        }
    }
}
