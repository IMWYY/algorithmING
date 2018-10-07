package leetCode.stack;

import java.util.*;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * create by stephen on 2018/10/7
 */
public class Problem103 {
    public static void main(String[] args) {
        int flag = 0;
        flag ^= 1;
        System.out.println(flag);
        flag ^= 1;
        System.out.println(flag);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 采用树的层次遍历的思路 利用一个栈来保存一层的节点
     * 利用左右节点入队列的顺序来实现不同的遍历顺序
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        Stack<TreeNode> stack = new Stack<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            stack.clear();
            List<Integer> temp = new ArrayList<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                stack.add(node);
                // 更简单的 可以不用stack 直接在这里判断是
//                temp.add(node.val);
//                temp.add(0, node.val);

            }
            res.add(temp);

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (res.size() % 2 == 1) {
                    if (node.right != null) queue.add(node.right);
                    if (node.left != null) queue.add(node.left);
                } else {
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }
        }
        return res;
    }

    /**
     * 递归遍历 遍历的时候保存一个level 根据level以不同顺序插入结果中即可
     */
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);
        return sol;
    }

    private void travel(TreeNode curr, List<List<Integer>> sol, int level) {
        if (curr == null) return;

        if (sol.size() <= level) {
            List<Integer> newLevel = new LinkedList<>();
            sol.add(newLevel);
        }

        List<Integer> collection = sol.get(level);
        if (level % 2 == 0) collection.add(curr.val);
        else collection.add(0, curr.val);

        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
}
