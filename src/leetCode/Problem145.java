package leetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * Example:
 * Input: [1,null,2,3]
 *  1
 *   \
 *    2
 *   /
 *  3
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * create by stephen on 2018/5/12
 */
public class Problem145 {

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
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = null;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if (cur.right == null || cur.right == pre) {
                result.add(cur.val);
                pre = cur;
                stack.pop();
                cur = null;
            } else {
                cur = cur.right;
            }
        }

        return result;
    }


    /**
     * 递归方法
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode node, List<Integer> sequence) {
        if (node == null) return;
        postOrder(node.left, sequence);
        postOrder(node.right, sequence);
        sequence.add(node.val);
    }
}
