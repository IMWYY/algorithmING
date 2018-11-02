package leetCode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * Output: [1,3,2]
 * <p>
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * create by stephen on 2018/5/12
 */
public class Problem094 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 非递归方法
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;

        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            temp = stack.pop();
            result.add(temp.val);
            temp = temp.right;
        }

        return result;
    }

    /**
     * 递归方法
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(TreeNode node, List<Integer> sequence) {
        if (node == null) return;
        inOrder(node.left, sequence);
        sequence.add(node.val);
        inOrder(node.right, sequence);
    }

    /**
     * mirror traversal
     * https://leetcode.com/articles/binary-tree-inorder-traversal/
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
