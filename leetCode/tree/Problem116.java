package leetCode.tree;

/**
 * Given a binary tree
 * <p>
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * <p>
 * Note:
 * You may only use constant extra space.
 * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * create by stephen on 2018/10/15
 */
public class Problem116 {
    private class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) {
            val = x;
        }
    }

    // recursive solution
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null) root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
    }

    // iterative solution:
    public void connect1(TreeLinkNode root) {
        if (root == null) return;
        for (TreeLinkNode leftMost = root; leftMost.left != null; leftMost = leftMost.left) {
            for (TreeLinkNode cur = leftMost; cur != null; cur = cur.next) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
            }
        }
    }
}
