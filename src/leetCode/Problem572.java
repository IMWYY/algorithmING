package leetCode;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and
 * node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this
 * node's descendants. The tree s could also be considered as a subtree of itself.
 * <p>
 * Example 1:
 * Given tree s:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * Given tree t:
 * 4
 * / \
 * 1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * <p>
 * create by stephen on 2018/6/10
 */
public class Problem572 {
    /**************************************************************
     * 非递归 利用（改进的）先序遍历来比较
     *************************************************************/
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String s1 = preOrder(s, true);
        String t1 = preOrder(t, true);
        return s1.contains(t1);
    }

    /**
     * 改进的先序遍历 改进点：
     * （1）左右空子树分开表示
     * （2）访问字符串前添加一个"#" 防止在比较s1.contains(t1)时出现错误
     * 比如t("23 4 lnull rull 5 lnull rnull") s("3 4 lnull rull 5 lnull rnull").
     */
    private String preOrder(TreeNode node, boolean left) {
        if (node == null) {
            if (left) {
                return "lnull";
            } else {
                return "rnull";
            }
        }
        return "#" + node.val + " " + preOrder(node.left, true) + " " + preOrder(node.right, false);
    }

    /**************************************************************
     * 递归 通过比较节点的值
     *************************************************************/
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        if (t == null) return true;
        if (s == null) return false;
        return equal(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);

    }

    private boolean equal(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return equal(a.left, b.left) && equal(a.right, b.right);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
