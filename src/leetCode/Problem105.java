package leetCode;

import java.util.Stack;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * <p>
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15  7
 * <p>
 * create by stephen on 2018/5/3
 */
public class Problem105 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归解法 注意递归的跳出条件
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length != preorder.length) return null;
        if (preorder.length == 0) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode construct(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2) {
        if (s1 < 0 || e1 >= preorder.length || s1 > e1 ||
                s2 < 0 || e2 >= inorder.length || s2 > e2) return null;
        if (e1 == s1 || e2 == s2) return new TreeNode(preorder[s1]);
        TreeNode root = new TreeNode(preorder[s1]);
        int rootIndex;
        for (rootIndex = s2; rootIndex <= e2; ++rootIndex) {
            if (inorder[rootIndex] == preorder[s1]) break;
        }
        int leftLen = rootIndex - s2, rightLen = e2 - rootIndex;
        root.left = leftLen == 0 ? null : construct(preorder, s1 + 1, s1 + leftLen, inorder, s2, rootIndex - 1);
        root.right = rightLen == 0 ? null : construct(preorder, s1 + leftLen + 1, e1, inorder, rootIndex + 1, e2);
        return root;
    }


    /**
     * 非递归写法
     * 维护两个指针 pre和in。
     * （1）将根节点压栈
     * （2）若栈顶元素不等于inorder[in] 将栈顶元素的左节点置为preorder[pre++]  一直到条件不成立
     * （3）若栈顶元素等于inorder[in] 出栈将栈顶元素的右节点置为preorder[pre++] 一直到条件不成立 并压栈新节点
     * （4）重复2-3
     * 事实上pre始终指向下一个要创建到树节点，inorder始终指向当前栈顶元素到最左节点。
     * 第3步中出栈目的是找到有右子树的节点并创建右子树（若没有右子树，会一直出栈到空）
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (inorder.length != preorder.length) return null;
        if (preorder.length == 0) return null;
        if (preorder.length == 1) return new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        int pre = 0, in = 0;
        TreeNode root = new TreeNode(preorder[pre++]), temp;
        stack.push(root);
        while (true) {
            while (stack.peek().val != inorder[in]) {
                temp = new TreeNode(preorder[pre++]);
                stack.peek().left = temp;
                stack.push(temp);
            }
            temp = null;
            while (!stack.isEmpty() && stack.peek().val == inorder[in]) {
                temp = stack.pop();
                in++;
            }
            if (pre == preorder.length) break;
            TreeNode right = new TreeNode(preorder[pre++]);
            if (temp != null) {
                temp.right = right;
            }
            stack.push(right);
        }
        return root;
    }

}
