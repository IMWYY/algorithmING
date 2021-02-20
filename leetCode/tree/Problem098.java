package leetCode.tree;

import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class Problem098 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // recursive solution
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (!isValidBST(root.right)) return false;

        TreeNode temp = root.left;
        if (temp != null) {
            while (temp.right != null) temp = temp.right;
            if (temp.val >= root.val) return false;
        }

        temp = root.right;
        if (temp != null) {
            while (temp.left != null) temp = temp.left;
            if (temp.val <= root.val) return false;
        }
        return true;
    }

    // inorder traversal, check whether it is an incresing sequences
    public boolean isValidBST1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root, pre = null;

        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            if (!stack.isEmpty()) {
                temp = stack.pop();
                if (pre != null && pre.val >= temp.val) return false;
                pre = temp;
                temp = temp.right;
            }
        }
        return true;
    }
}