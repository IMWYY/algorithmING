package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example:
 * Input: [1,null,2,3]
 *  1
 *   \
 *    2
 *   /
 *  3
 * Output: [1,2,3]
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * create by stephen on 2018/5/12
 */
public class Problem144 {

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
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;

        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                result.add(temp.val);
                stack.push(temp);
                temp = temp.left;
            }

            temp = stack.pop();
            temp = temp.right;
        }

        return result;
    }


    /**
     * 递归方法
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(TreeNode node, List<Integer> sequence) {
        if (node == null) return;
        sequence.add(node.val);
        preOrder(node.left, sequence);
        preOrder(node.right, sequence);
    }

}
