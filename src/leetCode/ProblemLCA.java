package leetCode;

/**
 * 最近公共父节点问题
 * create by stephen on 2018/4/21
 */
public class ProblemLCA {

    public class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
    }

    /**
     * 计算任意两个节点之间的距离
     * 思路：
     * 对于任意两个节点，只可能有两种情况
     * 1）两个节点分别在根节点的两边
     * 2）两个节点在根节点的一边
     * 对于第一种情况，只需要计算两个节点到根节点到距离
     * 对于第二种情况，需要计算两个节点的最近公共祖先lca
     * result = distance(root, a) + distance(root, b) - 2 * distance(root, lca)
     */
    public int ditanceBetweenTwoNode(TreeNode root, TreeNode a, TreeNode b) {
        int result = 0;
        Problem236

    }

    public int getDistanceFromRoot(TreeNode root, TreeNode node) {

    }


    public class UFset {
        public TreeNode cur;
        public TreeNode parent;
        public int h;

        public UFset(TreeNode cur, TreeNode parent, int h) {
            this.cur = cur;
            this.parent = parent;
            this.h = h;
        }
    }


    /**
     * 计算两个节点的最近父节点
     */
    public TreeNode getLCA(TreeNode root, TreeNode a, TreeNode b) {

    }

}
