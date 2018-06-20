package leetCode;

import java.util.*;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can
 * be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed
 * later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can
 * be serialized to a string and this string can be deserialized to the original tree structure.
 * create by stephen on 2018/6/13
 */
public class Problem297 {

    /*************************************
     * 改进 只用先序遍历就够了 需要记录null节点
     ************************************/
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        buildString(root, builder);
        return builder.toString();
    }

    private void buildString(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("X").append(",");
        } else {
            builder.append(root.val).append(",");
            buildString(root.left, builder);
            buildString(root.right, builder);
        }
    }

    public TreeNode deserialize(String data) {
        Deque<String> queue = new ArrayDeque<>();
        queue.addAll(Arrays.asList(data.split(",")));
        return buildTree(queue);
    }

    private TreeNode buildTree(Deque<String> queue) {
        String val = queue.remove();
        if (val.equals("X")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        return root;
    }

    /*************************************
     * 利用中序遍历和先序遍历 同时利用一个map记录每个节点的索引 用来区分val相同的节点
     * note:
     *  1.#1#2 和 1#2# spilt的结果不同 前者数组多一位 且第一位为""
     *  2.null + "," 之后，null变成了"null"
     *
     ************************************/
    public String serialize1(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();     // key point: 为了防止重复的值干扰结果
        return preOrder(root, map) + "," + inOrder(root, map);
    }

    public TreeNode deserialize1(String data) {
        String[] strings = data.split(",");
        if (strings[0] == null || strings[1] == null || strings[0].equals("null") || strings[1].equals("null"))
            return null;
        String[] pre = strings[0].split("#");
        String[] in = strings[1].split("#");

        return construct(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }

    private TreeNode construct(String[] pre, String[] in, int preS, int preE, int inS, int inE) {
        if (preS > preE || preS >= pre.length) return null;
        TreeNode root = new TreeNode(Integer.valueOf(pre[preS].substring(0, pre[preS].length() - 1)));
        if (preS == preE) return root;

        int pos;
        for (pos = inS; pos <= inE; ++pos) {
            if (in[pos].equals(pre[preS])) break;
        }

        root.left = construct(pre, in, preS + 1, preS + pos - inS, inS, pos - 1);
        root.right = construct(pre, in, preS + pos - inS + 1, preE, pos + 1, inE);

        return root;
    }

    private String preOrder(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        TreeNode ptr = root;
        int index = 0;
        while (ptr != null || !stack.isEmpty()) {
            while (ptr != null) {
                stack.push(ptr);
                map.put(ptr, index++);
                builder.append(ptr.val).append((char) (map.getOrDefault(ptr, 0) + 'a')).append("#");

                ptr = ptr.left;
            }
            if (!stack.isEmpty()) {
                ptr = stack.pop();
                ptr = ptr.right;
            }
        }
        return builder.toString();
    }

    private String inOrder(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        TreeNode ptr = root;
        while (ptr != null || !stack.isEmpty()) {
            while (ptr != null) {
                stack.push(ptr);
                ptr = ptr.left;
            }
            if (!stack.isEmpty()) {
                ptr = stack.pop();
                builder.append(ptr.val).append((char) (map.getOrDefault(ptr, 0) + 'a')).append("#");
                ptr = ptr.right;
            }
        }
        return builder.toString();
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
