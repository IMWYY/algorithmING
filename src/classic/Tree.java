package classic;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的相关算法
 * create by stephen on 2018/4/19
 */
public class Tree {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 前序遍历非递归
     * 利用栈，在push的时候访问节点
     */
    public void preOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        Node temp = node;

        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                System.out.println(temp.value);
                temp = temp.left;
            }

            if (!stack.isEmpty()) {
                temp = stack.pop();
                temp = temp.right;
            }
        }
    }

    /**
     * 中序遍历非递归
     * 利用栈，在pop的时候访问节点
     */
    public void inOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        Node temp = node;

        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }

            if (!stack.isEmpty()) {
                temp = stack.pop();
                System.out.println(temp.value);
                temp = temp.right;
            }
        }
    }

    /**
     * 后序遍历非递归
     * 利用栈和一个temp节点，只有当右子树为空或者🈶️右子树被访问过才会访问根节点
     */
    public void postOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        Node cur = node, temp = null, pre = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            temp = stack.peek();
            if (temp.right == null || temp.right == pre) {
                temp = stack.pop();
                System.out.println(temp.value);
                pre = temp;
                cur = null;
            } else {
                cur = temp.right;
            }
        }

    }

    /**
     * 层次遍历：利用队列
     * @param node
     */
    public void levelOrder(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        Node temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.println(temp.value);
            if (temp.left != null) {
                queue.offer(temp.left);
            }

            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
    }

    /**
     * 非递归求树高度
     */
    public int height(Node node) {
       Queue<Node> queue = new ArrayDeque<>();
       Node temp = node;
       queue.offer(temp);
       int h = 0, len;
       while (!queue.isEmpty()) {
            len = queue.size();
            h ++;
            while (len-- > 0) {
                temp = queue.poll();
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
            }
       }

       return h;
    }

}
