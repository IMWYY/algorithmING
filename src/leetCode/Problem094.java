package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 *  1
 *   \
 *   2
 *  /
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

    public void inOrder(TreeNode node, List<Integer> sequence) {
        if (node == null) return;
        inOrder(node.left, sequence);
        sequence.add(node.val);
        inOrder(node.right, sequence);
    }


}
