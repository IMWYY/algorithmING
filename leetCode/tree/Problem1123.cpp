
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

using namespace std;

/**
 * Given a rooted binary tree, find the lowest common ancestor of its deepest
 * leaves.
 *
 * Recall that:
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0, and if the depth of a node is d, the
 * depth of each of its children is d+1. The lowest common ancestor of a set S
of
 * nodes is the node A with the largest depth such that every node in S is in
 the
 * subtree with root A.

 */

struct TreeNode {
  int val;
  TreeNode* left;
  TreeNode* right;
  TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

// LCA problem. use memorization to remember the depth of sub-tree
int depth_of_tree(TreeNode* node, std::vector<int>& levels) {
  if (node == nullptr) 0;
  if (levels[node->val] != -1) return levels[node->val];
  int res = 0;
  if (node->left != nullptr && node->right != nullptr) {
    res = std::max(depth_of_tree(node->left, levels),
                   depth_of_tree(node->right, levels)) +
          1;
  } else if (node->left != nullptr) {
    res = depth_of_tree(node->left, levels) + 1;
  } else if (node->right != nullptr) {
    res = depth_of_tree(node->right, levels) + 1;
  } else {
    res = 1;
  }
  levels[node->val] = res;
  return res;
}

TreeNode* find_lca(TreeNode* node, std::vector<int>& levels) {
  if (node == nullptr) return node;
  if (node->left != nullptr && node->right != nullptr) {
    int l_d = depth_of_tree(node->left, levels);
    int r_d = depth_of_tree(node->right, levels);
    if (l_d == r_d) {
      return node;
    } else if (l_d > r_d) {
      return find_lca(node->left, levels);
    } else {
      return find_lca(node->right, levels);
    }
  } else if (node->left != nullptr) {
    return find_lca(node->left, levels);
  } else if (node->right != nullptr) {
    return find_lca(node->right, levels);
  } else {
    return node;
  }
}

TreeNode* lcaDeepestLeaves(TreeNode* root) {
  if (root == nullptr || (root->left == nullptr && root->right == nullptr))
    return root;
  std::vector<int> levels(1001, -1);
  TreeNode* res = find_lca(root, levels);
  return res;
}