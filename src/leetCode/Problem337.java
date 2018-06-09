package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area,
 * called the "root." Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 * create by stephen on 2018/6/7
 */
public class Problem337 {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /*******************优化解法*********************
     * 相比自己写的方法 更清晰的表达
     */
    public int rob(TreeNode root) {
        int[] result = dfs(root);
        return Math.max(result[0], result[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int[] res = new int[2];
        res[0] = l[1] + r[1] + root.val;
        res[1] = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return res;
    }



    private Map<TreeNode, int[]> map = new HashMap<>();

    /*******************未优化解法*********************
     * 递归 同时利用了memorization减少运算
     * key-TreeNode, value-int[] 0:选中 1:不选
     */
    public int rob1(TreeNode root) {
        return Math.max(rob(root, true), rob(root, false));
    }

    private int rob(TreeNode root, boolean includeRoot) {
        if (root == null) return 0;
        int result, leftF, leftT, rightF, rightT;

        if (map.get(root.left) != null && map.get(root.left)[1] >= 0) {
            leftF = map.get(root.left)[1];
        } else {
            leftF = rob(root.left, false);
        }

        if (map.get(root.right) != null && map.get(root.right)[1] >= 0) {
            rightF = map.get(root.right)[1];
        } else {
            rightF = rob(root.right, false);
        }

        if (includeRoot) {
            result = root.val + rightF + leftF;

            if (map.get(root) == null) {
                int[] temp = new int[]{result, -1};
                map.put(root, temp);
            } else {
                map.get(root)[0] = result;
            }

            return result;
        } else {
            if (map.get(root.left) != null && map.get(root.left)[0] >= 0) {
                leftT = map.get(root.left)[0];
            } else {
                leftT = rob(root.left, true);
            }
            if (map.get(root.right) != null && map.get(root.right)[0] >= 0) {
                rightT = map.get(root.right)[0];
            } else {
                rightT = rob(root.right, true);
            }

            result = Math.max(Math.max(Math.max(rightT + leftT,
                    rightT + leftF),
                    rightF + leftT),
                    rightF + leftF);


            if (map.get(root) == null) {
                int[] temp = new int[]{-1, result};
                map.put(root, temp);
            } else {
                map.get(root)[1] = result;
            }

            return result;
        }
    }
}
