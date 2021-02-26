package leetCode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree
 */
public class Problem654 {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * recursive solution
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return constructTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructTree(int[] nums, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(nums[start]);

        int maxIndex = start;
        for (int i = start + 1; i <= end; ++i) {
            if (nums[i] > nums[maxIndex]) maxIndex = i;
        }
        TreeNode m = new TreeNode(nums[maxIndex]);
        m.left = constructTree(nums, start, maxIndex - 1);
        m.right = constructTree(nums, maxIndex + 1, end);
        return m;
    }


    /**
     * 非递归解法
     * 对于每个新加入的元素 找到其应该插入的位置
     */
    public TreeNode constructMaximumBinaryTree1(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for (int num : nums) {
            TreeNode curr = new TreeNode(num);
            // 将所有比他小的元素放入左子数
            // 在执行这一步的时候，前面所有比他小的元素 已经按照右到左 小到大的顺序排好了
            while (!stack.isEmpty() && stack.peek().val < num) {
                curr.left = stack.pop();
            }
            // 如果当前元素不是最大 放到比他大的元素的右子树
            if (!stack.isEmpty()) {
                stack.peek().right = curr;
            }
            stack.push(curr);
        }
        return stack.isEmpty() ? null : stack.removeLast();
    }
}
