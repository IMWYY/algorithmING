package leetCode.tree;

import classic.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最近公共父节点问题
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes v and w as the lowest
 * node in T that has both v and w as descendants
 * (where we allow a node to be a descendant of itself).”
 * create by stephen on 2018/4/21
 */
public class Problem236 {

	/**
	 * 递归解决
	 * 函数的语义：
	 * 如果子树中包含了p和q，那么直接返回公共节点；
	 * 如果子树中仅包含一个节点，返回该节点；
	 * 如果子树中不包含任何节点 返回null
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == root || q == root)
			return root;
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		return left == null ? right : right == null ? left : root;
	}

	/**
	 * 层次遍历后为每个节点保存他的父节点
	 */
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		// Stack for tree traversal
		Deque<TreeNode> stack = new ArrayDeque<>();

		// HashMap for parent pointers
		Map<TreeNode, TreeNode> parent = new HashMap<>();

		parent.put(root, null);
		stack.push(root);

		// Iterate until we find both the nodes p and q
		while (!parent.containsKey(p) || !parent.containsKey(q)) {

			TreeNode node = stack.pop();

			// While traversing the tree, keep saving the parent pointers.
			if (node.left != null) {
				parent.put(node.left, node);
				stack.push(node.left);
			}
			if (node.right != null) {
				parent.put(node.right, node);
				stack.push(node.right);
			}
		}

		// Ancestors set() for node p.
		Set<TreeNode> ancestors = new HashSet<>();

		// Process all ancestors for node p using parent pointers.
		while (p != null) {
			ancestors.add(p);
			p = parent.get(p);
		}

		// The first ancestor of q which appears in
		// p's ancestor set() is their lowest common ancestor.
		while (!ancestors.contains(q))
			q = parent.get(q);
		return q;
	}

	/**
	 * 转换为rmq问题解决
	 * <p>
	 * 参考：https://www.cnblogs.com/scau20110726/archive/2013/05/26/3100812.html
	 */
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		// TODO LCA和RMQ
		// DFS遍历树
		// 记录每个几点的树深度
		// 求遍历序列中第一次出现的节点之间的最小值 对应的节点即为LCA
		return null;
	}

	/**
	 * Tarjan算法 利用并查集和DFS
	 * marge和find为并查集合并函数和查找函数
	 * <p>
	 * Tarjan(u) {
	 * for each(u,v) {    //访问所有u子节点v
	 * Tarjan(v);        //继续往下遍历
	 * marge(u,v);    //合并v到u上
	 * 标记v被访问过;
	 * }
	 * <p>
	 * for each(u,e) {    //访问所有和u有询问关系的e
	 * 如果e被访问过;
	 * u,e的最近公共祖先为find(e);
	 * }
	 * }
	 */
	public TreeNode Tarjan(TreeNodeWrapper root, TreeNodeWrapper p, TreeNodeWrapper q, UFset uFset) {
		if (root.node.left != null) {
			Tarjan(root.left, p, q, uFset);
			// TODO: merge
			// TODO: mark visted
		}

		if (root.node.right != null) {
			Tarjan(root.right, p, q, uFset);
			// TODO: merge
			// TODO: mark visted
		}

		return null;
	}

	public TreeNodeWrapper wrapTreeNode(TreeNode node) {
		if (node == null)
			return new TreeNodeWrapper(null);
		TreeNodeWrapper c = wrapTreeNode(node);
		TreeNodeWrapper l = wrapTreeNode(node.left);
		TreeNodeWrapper r = wrapTreeNode(node.right);
		c.left = l;
		c.right = r;
		return c;
	}

	public class TreeNodeWrapper {
		public TreeNode node;
		public TreeNodeWrapper parent;
		public TreeNodeWrapper left;
		public TreeNodeWrapper right;
		public boolean visited;

		public TreeNodeWrapper(TreeNode node) {
			this.node = node;
		}
	}

	public class UFset {
		/**
		 * 查找并返回节点所属集合的根节点
		 *
		 * @param x 搜索节点
		 * @return 根节点
		 */
		public TreeNodeWrapper find(TreeNodeWrapper x) {
			TreeNodeWrapper s; //查找位置
			for (s = x; s.parent != null; s = s.parent)
				;
			while (s != x) { //优化方案―压缩路径，使后续的查找操作加速。
				TreeNodeWrapper tmp = x.parent;
				x.parent = s;
				x = tmp;
			}
			return s;
		}

		/**
		 * 将两个不同集合的元素进行合并，使两个集合中任两个元素都连通
		 */

		public void union(TreeNodeWrapper x1, TreeNodeWrapper x2) {
			TreeNodeWrapper r2 = find(x2); //r1 为 x1 的根结点，r2 为 x2 的根结点
			r2.parent = find(x1);
		}
	}
}
