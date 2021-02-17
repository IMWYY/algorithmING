package leetCode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 */
public class Problem315 {
    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count = 1;
        public TreeNode(int val) { this.val = val;}
    }

    // augmented BST, store the number of nodes in its left subtree in root node
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        result.add(0);
        for (int i = nums.length - 2; i >= 0; --i) {
            result.add(insert(root, nums[i]));
        }
        Collections.reverse(result);
        return result;
    }

    // return the number of nodes smaller than val
    private int insert(TreeNode root, int val) {
        int count = 0;
        while (true) {
            if (val <= root.val) {
                root.count++; // update counter in root node
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                count += root.count; // update result
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return count;
    }
}
