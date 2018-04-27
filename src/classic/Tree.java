package classic;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的相关算法
 * create by stephen on 2018/4/19
 */
public class Tree {

    /**
     * 前序遍历非递归
     * 利用栈，在push的时候访问节点
     */
    public void preOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = node;

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
    public void inOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = node;

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
    public void postOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = node, temp = null, pre = null;
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
    public void levelOrder(TreeNode node) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(node);
        TreeNode temp;
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
    public int height(TreeNode node) {
       Queue<TreeNode> queue = new ArrayDeque<>();
       TreeNode temp = node;
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

    /*********************Mirror方法遍历 空间复杂度O（1）***************************/

    /**
     * Mirror方法中序遍历
     * 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
     * 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
     * a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
     * b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
     * 重复以上直到当前节点为空。
     */
    public void mirrorInOrder(TreeNode root) {
        TreeNode cur = root, temp;
        while (cur != null) {
            if (cur.left == null) {
                System.out.println(cur.value);
                cur = cur.right;
            }

            temp = cur;
            while (temp.right != null && temp.right != cur) {
                temp = temp.right;
            }

            if (temp.right == null) {
                temp.right = cur;
            } else {
                temp.right = null;
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }

    /**
     * 先序遍历
     * 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
     * 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
     * a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。输出当前节点（在这里输出，这是与中序遍历唯一一点不同）。当前节点更新为当前节点的左孩子。
     * b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。当前节点更新为当前节点的右孩子。
     * 重复以上1、2直到当前节点为空
     */
    public void mirrorPreOrder(TreeNode root) {
        TreeNode cur = root, temp;
        while (cur != null) {
            if (cur.left == null) {
                System.out.println(cur.value);
                cur = cur.right;
            }

            temp = cur;
            while (temp.right != null && temp.right != cur) {
                temp = temp.right;
            }

            if (temp.right == null) {
                temp.right = cur;
                System.out.println(cur.value);      //和中序遍历唯一的不同在这里
            } else {
                temp.right = null;
                cur = cur.right;
            }
        }
    }

    /**
     * 后序遍历
     * 如果当前节点的左孩子为空，则将其右孩子作为当前节点。
     * 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
     * a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
     * b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。倒序输出从当前节点的左孩子到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右孩子。
     * 重复以上1、2直到当前节点为空。
     * @param root
     */
    public void mirrorPostOrder(TreeNode root) {
        TreeNode cur = root, temp;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            }

            temp = cur;
            while (temp.right != null && temp.right != cur) {
                temp = temp.right;
            }

            if (temp.right == null) {
                temp.right = cur;
            } else {
                printReverse(cur.left, temp);
                temp.right = null;
                cur = cur.right;
            }
        }
    }

    /**
     * 逆序输出节点值
     */
    public void printReverse(TreeNode start, TreeNode end) {
        Stack<TreeNode> stack = new Stack<>();
        while (start != end) {
            stack.push(start);
            start = start.right;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().value);
        }
    }

}
