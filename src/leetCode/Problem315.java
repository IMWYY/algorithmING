package leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 * Example:
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * create by stephen on 2018/5/25
 */
public class Problem315 {
    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count = 1;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 利用BST 增加一个count值记录小于等于当前节点的数量
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        TreeNode root = new TreeNode(nums[nums.length-1]);
        result.add(0);
        for (int i=nums.length-2; i>=0; --i) {
            result.add(insert(root, nums[i]));
        }

        Collections.reverse(result);
        return result;
    }

    private int insert(TreeNode root, int val) {
        int count = 0;
        while (true) {
            if (val <= root.val) {
                root.count ++;
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                count += root.count;
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
